(function(root) {
  var SQLParser = function() {
    function require(path){ return require[path]; }
    require['./lexer'] = new function() {
  var exports = this;
  (function() {
  var Lexer;

  Lexer = (function() {
    var BOOLEAN, DBLSTRING, LITERAL, MATH, MATH_MULTI, NUMBER, SEPARATOR, SQL_CONDITIONALS, SQL_FUNCTIONS, SQL_KEYWORDS, SQL_OPERATORS, SQL_SORT_ORDERS, STAR, STRING, SUB_SELECT_OP, SUB_SELECT_UNARY_OP, TERMINAL, WHITESPACE;

    function Lexer(sql, opts) {
      var bytesConsumed, i;
      if (opts == null) opts = {};
      this.sql = sql;
      this.preserveWhitespace = opts.preserveWhitespace || false;
      this.tokens = [];
      this.currentLine = 1;
      i = 0;
      while (this.chunk = sql.slice(i)) {
        bytesConsumed = this.keywordToken() || this.starToken() || this.booleanToken() || this.functionToken() || this.windowExtension() || this.sortOrderToken() || this.seperatorToken() || this.operatorToken() || this.mathToken() || this.dotToken() || this.conditionalToken() || this.subSelectOpToken() || this.subSelectUnaryOpToken() || this.numberToken() || this.stringToken() || this.parensToken() || this.whitespaceToken() || this.literalToken() || this.terminalToken();
        if (bytesConsumed < 1) {
          throw new Error("NOTHING CONSUMED: Stopped at - '" + (this.chunk.slice(0, 30)) + "'");
        }
        i += bytesConsumed;
      }
      this.token('EOF', '');
    }

    Lexer.prototype.token = function(name, value) {
      return this.tokens.push([name, value, this.currentLine]);
    };

    Lexer.prototype.tokenizeFromRegex = function(name, regex, part, lengthPart, output) {
      var match, partMatch;
      if (part == null) part = 0;
      if (lengthPart == null) lengthPart = part;
      if (output == null) output = true;
      if (!(match = regex.exec(this.chunk))) return 0;
      partMatch = match[part];
      if (output) this.token(name, partMatch);
      return match[lengthPart].length;
    };

    Lexer.prototype.tokenizeFromWord = function(name, word) {
      var match, matcher;
      if (word == null) word = name;
      word = this.regexEscape(word);
      matcher = /^\w+$/.test(word) ? new RegExp("^(" + word + ")\\b", 'ig') : new RegExp("^(" + word + ")", 'ig');
      match = matcher.exec(this.chunk);
      if (!match) return 0;
      this.token(name, match[1]);
      return match[1].length;
    };

    Lexer.prototype.tokenizeFromList = function(name, list) {
      var entry, ret, _i, _len;
      ret = 0;
      for (_i = 0, _len = list.length; _i < _len; _i++) {
        entry = list[_i];
        ret = this.tokenizeFromWord(name, entry);
        if (ret > 0) break;
      }
      return ret;
    };

    Lexer.prototype.keywordToken = function() {
      return this.tokenizeFromWord('SELECT') || this.tokenizeFromWord('DISTINCT') || this.tokenizeFromWord('FROM') || this.tokenizeFromWord('WHERE') || this.tokenizeFromWord('GROUP') || this.tokenizeFromWord('ORDER') || this.tokenizeFromWord('BY') || this.tokenizeFromWord('HAVING') || this.tokenizeFromWord('LIMIT') || this.tokenizeFromWord('JOIN') || this.tokenizeFromWord('LEFT') || this.tokenizeFromWord('RIGHT') || this.tokenizeFromWord('INNER') || this.tokenizeFromWord('OUTER') || this.tokenizeFromWord('ON') || this.tokenizeFromWord('AS') || this.tokenizeFromWord('UNION') || this.tokenizeFromWord('ALL') || this.tokenizeFromWord('CASE') || this.tokenizeFromWord('WHEN') || this.tokenizeFromWord('THEN') || this.tokenizeFromWord('ELSE') || this.tokenizeFromWord('END');
    };

    Lexer.prototype.dotToken = function() {
      return this.tokenizeFromWord('DOT', '.');
    };

    Lexer.prototype.operatorToken = function() {
      return this.tokenizeFromList('OPERATOR', SQL_OPERATORS);
    };

    Lexer.prototype.mathToken = function() {
      return this.tokenizeFromList('MATH', MATH) || this.tokenizeFromList('MATH_MULTI', MATH_MULTI);
    };

    Lexer.prototype.conditionalToken = function() {
      return this.tokenizeFromList('CONDITIONAL', SQL_CONDITIONALS);
    };

    Lexer.prototype.subSelectOpToken = function() {
      return this.tokenizeFromList('SUB_SELECT_OP', SUB_SELECT_OP);
    };

    Lexer.prototype.subSelectUnaryOpToken = function() {
      return this.tokenizeFromList('SUB_SELECT_UNARY_OP', SUB_SELECT_UNARY_OP);
    };

    Lexer.prototype.functionToken = function() {
      return this.tokenizeFromList('FUNCTION', SQL_FUNCTIONS);
    };

    Lexer.prototype.sortOrderToken = function() {
      return this.tokenizeFromList('DIRECTION', SQL_SORT_ORDERS);
    };

    Lexer.prototype.booleanToken = function() {
      return this.tokenizeFromList('BOOLEAN', BOOLEAN);
    };

    Lexer.prototype.starToken = function() {
      return this.tokenizeFromRegex('STAR', STAR);
    };

    Lexer.prototype.seperatorToken = function() {
      return this.tokenizeFromRegex('SEPARATOR', SEPARATOR);
    };

    Lexer.prototype.terminalToken = function() {
      return this.tokenizeFromRegex('TERMINAL', TERMINAL);
    };

    Lexer.prototype.literalToken = function() {
      return this.tokenizeFromRegex('LITERAL', LITERAL, 1, 0);
    };

    Lexer.prototype.numberToken = function() {
      return this.tokenizeFromRegex('NUMBER', NUMBER);
    };

    Lexer.prototype.stringToken = function() {
      return this.tokenizeFromRegex('STRING', STRING, 1, 0) || this.tokenizeFromRegex('DBLSTRING', DBLSTRING, 1, 0);
    };

    Lexer.prototype.parensToken = function() {
      return this.tokenizeFromRegex('LEFT_PAREN', /^\(/) || this.tokenizeFromRegex('RIGHT_PAREN', /^\)/);
    };

    Lexer.prototype.windowExtension = function() {
      var match;
      match = /^\.(win):(length|time)/i.exec(this.chunk);
      if (!match) return 0;
      this.token('WINDOW', match[1]);
      this.token('WINDOW_FUNCTION', match[2]);
      return match[0].length;
    };

    Lexer.prototype.whitespaceToken = function() {
      var match, newlines, partMatch;
      if (!(match = WHITESPACE.exec(this.chunk))) return 0;
      partMatch = match[0];
      newlines = partMatch.replace(/[^\n]/, '').length;
      this.currentLine += newlines;
      if (this.preserveWhitespace) this.token(name, partMatch);
      return partMatch.length;
    };

    Lexer.prototype.regexEscape = function(str) {
      return str.replace(/[-[\]{}()*+?.,\\^$|#\s]/g, "\\$&");
    };

    SQL_KEYWORDS = ['SELECT', 'FROM', 'WHERE', 'GROUP BY', 'ORDER BY', 'HAVING', 'AS'];

    SQL_FUNCTIONS = ['AVG', 'COUNT', 'MIN', 'MAX', 'SUM'];

    SQL_SORT_ORDERS = ['ASC', 'DESC'];

    SQL_OPERATORS = ['=', '!=', '>=', '>', '<=', '<>', '<', 'LIKE', 'IS NOT', 'IS'];

    SUB_SELECT_OP = ['IN', 'NOT IN', 'ANY', 'ALL', 'SOME'];

    SUB_SELECT_UNARY_OP = ['EXISTS'];

    SQL_CONDITIONALS = ['AND', 'OR'];

    BOOLEAN = ['TRUE', 'FALSE', 'NULL'];

    MATH = ['+', '-'];

    MATH_MULTI = ['/', '*'];

    STAR = /^\*/;

    SEPARATOR = /^,/;

    TERMINAL = /^;/;

    WHITESPACE = /^[ \n\r\t]+/;

    LITERAL = /^`?([a-z_][a-z0-9_]{0,})`?/i;

    NUMBER = /^[0-9]+(\.[0-9]+)?/;

    STRING = /^'([^\\']*(?:\\.[^\\']*)*)'/;

    DBLSTRING = /^"([^\\"]*(?:\\.[^\\"]*)*)"/;

    return Lexer;

  })();

  exports.tokenize = function(sql, opts) {
    return (new Lexer(sql, opts)).tokens;
  };

}).call(this);

};require['./compiled_parser'] = new function() {
  var exports = this;
  /* parser generated by jison 0.4.13 */
/*
  Returns a Parser object of the following structure:

  Parser: {
    yy: {}
  }

  Parser.prototype: {
    yy: {},
    trace: function(),
    symbols_: {associative list: name ==> number},
    terminals_: {associative list: number ==> name},
    productions_: [...],
    performAction: function anonymous(yytext, yyleng, yylineno, yy, yystate, $$, _$),
    table: [...],
    defaultActions: {...},
    parseError: function(str, hash),
    parse: function(input),

    lexer: {
        EOF: 1,
        parseError: function(str, hash),
        setInput: function(input),
        input: function(),
        unput: function(str),
        more: function(),
        less: function(n),
        pastInput: function(),
        upcomingInput: function(),
        showPosition: function(),
        test_match: function(regex_match_array, rule_index),
        next: function(),
        lex: function(),
        begin: function(condition),
        popState: function(),
        _currentRules: function(),
        topState: function(),
        pushState: function(condition),

        options: {
            ranges: boolean           (optional: true ==> token location info will include a .range[] member)
            flex: boolean             (optional: true ==> flex-like lexing behaviour where the rules are tested exhaustively to find the longest match)
            backtrack_lexer: boolean  (optional: true ==> lexer regexes are tested in order and for each matching regex the action code is invoked; the lexer terminates the scan when a token is returned by the action code)
        },

        performAction: function(yy, yy_, $avoiding_name_collisions, YY_START),
        rules: [...],
        conditions: {associative list: name ==> set},
    }
  }


  token location info (@$, _$, etc.): {
    first_line: n,
    last_line: n,
    first_column: n,
    last_column: n,
    range: [start_number, end_number]       (where the numbers are indexes into the input string, regular zero-based)
  }


  the parseError function receives a 'hash' object with these members for lexer and parser errors: {
    text:        (matched text)
    token:       (the produced terminal token, if any)
    line:        (yylineno)
  }
  while parser (grammar) errors will also provide these members, i.e. parser errors deliver a superset of attributes: {
    loc:         (yylloc)
    expected:    (string describing the set of expected tokens)
    recoverable: (boolean: TRUE when the parser has a error recovery rule available for this particular error)
  }
*/
var parser = (function(){
var parser = {trace: function trace() { },
yy: {},
symbols_: {"error":2,"Root":3,"Query":4,"EOF":5,"TERMINAL":6,"SelectQuery":7,"Unions":8,"SelectWithLimitQuery":9,"BasicSelectQuery":10,"Select":11,"OrderClause":12,"GroupClause":13,"LimitClause":14,"SelectClause":15,"WhereClause":16,"SELECT":17,"Fields":18,"FROM":19,"Table":20,"DISTINCT":21,"Joins":22,"Literal":23,"AS":24,"LEFT_PAREN":25,"List":26,"RIGHT_PAREN":27,"WINDOW":28,"WINDOW_FUNCTION":29,"Number":30,"Union":31,"UNION":32,"ALL":33,"Join":34,"JOIN":35,"ON":36,"Expression":37,"LEFT":38,"RIGHT":39,"INNER":40,"OUTER":41,"SEPARATOR":42,"WHERE":43,"LIMIT":44,"ORDER":45,"BY":46,"OrderArgs":47,"OrderArg":48,"Value":49,"DIRECTION":50,"GroupBasicClause":51,"HavingClause":52,"GROUP":53,"ArgumentList":54,"HAVING":55,"MATH":56,"MATH_MULTI":57,"OPERATOR":58,"CONDITIONAL":59,"SUB_SELECT_OP":60,"SubSelectExpression":61,"SUB_SELECT_UNARY_OP":62,"CaseExpression":63,"String":64,"Function":65,"UserFunction":66,"Boolean":67,"NUMBER":68,"BOOLEAN":69,"STRING":70,"DBLSTRING":71,"LITERAL":72,"DOT":73,"Star":74,"STAR":75,"FUNCTION":76,"AggregateArgumentList":77,"CASE":78,"CaseExpressionWhens":79,"CaseExpressionElse":80,"END":81,"CaseExpressionWhen":82,"WHEN":83,"THEN":84,"ELSE":85,"Field":86,"$accept":0,"$end":1},
terminals_: {2:"error",5:"EOF",6:"TERMINAL",17:"SELECT",19:"FROM",21:"DISTINCT",24:"AS",25:"LEFT_PAREN",27:"RIGHT_PAREN",28:"WINDOW",29:"WINDOW_FUNCTION",32:"UNION",33:"ALL",35:"JOIN",36:"ON",38:"LEFT",39:"RIGHT",40:"INNER",41:"OUTER",42:"SEPARATOR",43:"WHERE",44:"LIMIT",45:"ORDER",46:"BY",50:"DIRECTION",53:"GROUP",55:"HAVING",56:"MATH",57:"MATH_MULTI",58:"OPERATOR",59:"CONDITIONAL",60:"SUB_SELECT_OP",62:"SUB_SELECT_UNARY_OP",68:"NUMBER",69:"BOOLEAN",70:"STRING",71:"DBLSTRING",72:"LITERAL",73:"DOT",75:"STAR",76:"FUNCTION",78:"CASE",81:"END",83:"WHEN",84:"THEN",85:"ELSE"},
productions_: [0,[3,2],[3,3],[4,1],[4,2],[7,1],[7,1],[10,1],[10,2],[10,2],[10,3],[9,2],[11,1],[11,2],[15,4],[15,5],[15,5],[15,6],[20,1],[20,2],[20,3],[20,3],[20,3],[20,4],[20,6],[8,1],[8,2],[31,2],[31,3],[22,1],[22,2],[34,4],[34,5],[34,5],[34,6],[34,6],[34,6],[34,6],[34,2],[16,2],[14,2],[12,3],[47,1],[47,3],[48,1],[48,2],[13,1],[13,2],[51,3],[52,2],[37,3],[37,3],[37,3],[37,3],[37,3],[37,5],[37,3],[37,2],[37,1],[37,1],[61,3],[49,1],[49,1],[49,1],[49,1],[49,1],[49,1],[26,1],[30,1],[67,1],[64,1],[64,1],[23,1],[23,3],[74,1],[74,3],[65,4],[66,4],[77,1],[77,1],[77,2],[54,1],[54,3],[63,5],[63,4],[63,4],[63,3],[79,2],[79,1],[82,4],[80,2],[18,1],[18,3],[86,1],[86,2],[86,3],[86,1]],
performAction: function anonymous(yytext, yyleng, yylineno, yy, yystate /* action[1] */, $$ /* vstack */, _$ /* lstack */) {
/* this == yyval */

var $0 = $$.length - 1;
switch (yystate) {
case 1:return this.$ = $$[$0-1];
break;
case 2:return this.$ = $$[$0-2];
break;
case 3:this.$ = $$[$0];
break;
case 4:this.$ = (function () {
        $$[$0-1].unions = $$[$0];
        return $$[$0-1];
      }());
break;
case 5:this.$ = $$[$0];
break;
case 6:this.$ = $$[$0];
break;
case 7:this.$ = $$[$0];
break;
case 8:this.$ = (function () {
        $$[$0-1].order = $$[$0];
        return $$[$0-1];
      }());
break;
case 9:this.$ = (function () {
        $$[$0-1].group = $$[$0];
        return $$[$0-1];
      }());
break;
case 10:this.$ = (function () {
        $$[$0-2].group = $$[$0-1];
        $$[$0-2].order = $$[$0];
        return $$[$0-2];
      }());
break;
case 11:this.$ = (function () {
        $$[$0-1].limit = $$[$0];
        return $$[$0-1];
      }());
break;
case 12:this.$ = $$[$0];
break;
case 13:this.$ = (function () {
        $$[$0-1].where = $$[$0];
        return $$[$0-1];
      }());
break;
case 14:this.$ = new yy.Select($$[$0-2], $$[$0], false);
break;
case 15:this.$ = new yy.Select($$[$0-2], $$[$0], true);
break;
case 16:this.$ = new yy.Select($$[$0-3], $$[$0-1], false, $$[$0]);
break;
case 17:this.$ = new yy.Select($$[$0-3], $$[$0-1], true, $$[$0]);
break;
case 18:this.$ = new yy.Table($$[$0]);
break;
case 19:this.$ = new yy.Table($$[$0-1], $$[$0]);
break;
case 20:this.$ = new yy.Table($$[$0-2], $$[$0]);
break;
case 21:this.$ = $$[$0-1];
break;
case 22:this.$ = new yy.SubSelect($$[$0-1]);
break;
case 23:this.$ = new yy.SubSelect($$[$0-2], $$[$0]);
break;
case 24:this.$ = new yy.Table($$[$0-5], null, $$[$0-4], $$[$0-3], $$[$0-1]);
break;
case 25:this.$ = [$$[$0]];
break;
case 26:this.$ = $$[$0-1].concat($$[$01]);
break;
case 27:this.$ = new yy.Union($$[$0]);
break;
case 28:this.$ = new yy.Union($$[$0], true);
break;
case 29:this.$ = [$$[$0]];
break;
case 30:this.$ = $$[$0-1].concat($$[$0]);
break;
case 31:this.$ = new yy.Join($$[$0-2], $$[$0]);
break;
case 32:this.$ = new yy.Join($$[$0-2], $$[$0], 'LEFT');
break;
case 33:this.$ = new yy.Join($$[$0-2], $$[$0], 'RIGHT');
break;
case 34:this.$ = new yy.Join($$[$0-2], $$[$0], 'LEFT', 'INNER');
break;
case 35:this.$ = new yy.Join($$[$0-2], $$[$0], 'RIGHT', 'INNER');
break;
case 36:this.$ = new yy.Join($$[$0-2], $$[$0], 'LEFT', 'OUTER');
break;
case 37:this.$ = new yy.Join($$[$0-2], $$[$0], 'RIGHT', 'OUTER');
break;
case 38:this.$ = new yy.Join($$[$0]);
break;
case 39:this.$ = new yy.Where($$[$0]);
break;
case 40:this.$ = new yy.Limit($$[$0]);
break;
case 41:this.$ = new yy.Order($$[$0]);
break;
case 42:this.$ = [$$[$0]];
break;
case 43:this.$ = $$[$0-2].concat($$[$0]);
break;
case 44:this.$ = new yy.OrderArgument($$[$0], 'ASC');
break;
case 45:this.$ = new yy.OrderArgument($$[$0-1], $$[$0]);
break;
case 46:this.$ = $$[$0];
break;
case 47:this.$ = (function () {
        $$[$0-1].having = $$[$0];
        return $$[$0-1];
      }());
break;
case 48:this.$ = new yy.Group($$[$0]);
break;
case 49:this.$ = new yy.Having($$[$0]);
break;
case 50:this.$ = $$[$0-1];
break;
case 51:this.$ = new yy.Op($$[$0-1], $$[$0-2], $$[$0]);
break;
case 52:this.$ = new yy.Op($$[$0-1], $$[$0-2], $$[$0]);
break;
case 53:this.$ = new yy.Op($$[$0-1], $$[$0-2], $$[$0]);
break;
case 54:this.$ = new yy.Op($$[$0-1], $$[$0-2], $$[$0]);
break;
case 55:this.$ = new yy.Op($$[$0-3], $$[$0-4], $$[$0-1]);
break;
case 56:this.$ = new yy.Op($$[$0-1], $$[$0-2], $$[$0]);
break;
case 57:this.$ = new yy.UnaryOp($$[$0-1], $$[$0]);
break;
case 58:this.$ = $$[$0];
break;
case 59:this.$ = $$[$0];
break;
case 60:this.$ = new yy.SubSelect($$[$0-1]);
break;
case 61:this.$ = $$[$0];
break;
case 62:this.$ = $$[$0];
break;
case 63:this.$ = $$[$0];
break;
case 64:this.$ = $$[$0];
break;
case 65:this.$ = $$[$0];
break;
case 66:this.$ = $$[$0];
break;
case 67:this.$ = new yy.ListValue($$[$0]);
break;
case 68:this.$ = new yy.NumberValue($$[$0]);
break;
case 69:this.$ = new yy.BooleanValue($$[$0]);
break;
case 70:this.$ = new yy.StringValue($$[$0], "'");
break;
case 71:this.$ = new yy.StringValue($$[$0], '"');
break;
case 72:this.$ = new yy.LiteralValue($$[$0]);
break;
case 73:this.$ = new yy.LiteralValue($$[$0-2], $$[$0]);
break;
case 74:this.$ = new yy.Star();
break;
case 75:this.$ = new yy.Star($$[$0-2]);
break;
case 76:this.$ = new yy.FunctionValue($$[$0-3], $$[$0-1]);
break;
case 77:this.$ = new yy.FunctionValue($$[$0-3], $$[$0-1], true);
break;
case 78:this.$ = new yy.Star();
break;
case 79:this.$ = new yy.ArgumentListValue($$[$0]);
break;
case 80:this.$ = new yy.ArgumentListValue($$[$0], true);
break;
case 81:this.$ = [$$[$0]];
break;
case 82:this.$ = $$[$0-2].concat($$[$0]);
break;
case 83:this.$ = new yy.CaseExpression($$[$0-3], $$[$0-2], $$[$0-1]);
break;
case 84:this.$ = new yy.CaseExpression($$[$0-2], $$[$0-1]);
break;
case 85:this.$ = new yy.CaseExpression(null, $$[$0-2], $$[$0-1]);
break;
case 86:this.$ = new yy.CaseExpression(null, $$[$0-1]);
break;
case 87:this.$ = $$[$0-1].concat($$[$0]);
break;
case 88:this.$ = [$$[$0]];
break;
case 89:this.$ = new yy.CaseExpressionWhen($$[$0-2], $$[$0]);
break;
case 90:this.$ = new yy.CaseExpressionElse($$[$0]);
break;
case 91:this.$ = [$$[$0]];
break;
case 92:this.$ = $$[$0-2].concat($$[$0]);
break;
case 93:this.$ = new yy.Field($$[$0]);
break;
case 94:this.$ = new yy.Field($$[$0-1], $$[$0]);
break;
case 95:this.$ = new yy.Field($$[$0-2], $$[$0]);
break;
case 96:this.$ = $$[$0];
break;
}
},
table: [{3:1,4:2,7:3,9:4,10:5,11:6,15:7,17:[1,8]},{1:[3]},{5:[1,9],6:[1,10]},{5:[2,3],6:[2,3],8:11,14:12,27:[2,3],31:13,32:[1,15],44:[1,14]},{5:[2,5],6:[2,5],27:[2,5],32:[2,5],44:[2,5]},{5:[2,6],6:[2,6],27:[2,6],32:[2,6],44:[2,6]},{5:[2,7],6:[2,7],12:16,13:17,27:[2,7],32:[2,7],44:[2,7],45:[1,18],51:19,53:[1,20]},{5:[2,12],6:[2,12],16:21,27:[2,12],32:[2,12],43:[1,22],44:[2,12],45:[2,12],53:[2,12]},{18:23,21:[1,24],23:33,25:[1,28],30:34,37:26,49:29,62:[1,30],63:31,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],74:27,75:[1,32],76:[1,44],78:[1,39],86:25},{1:[2,1]},{5:[1,46]},{5:[2,4],6:[2,4],27:[2,4],31:47,32:[1,15]},{5:[2,11],6:[2,11],27:[2,11],32:[2,11],44:[2,11]},{5:[2,25],6:[2,25],27:[2,25],32:[2,25]},{30:48,68:[1,41]},{7:49,9:4,10:5,11:6,15:7,17:[1,8],33:[1,50]},{5:[2,8],6:[2,8],27:[2,8],32:[2,8],44:[2,8]},{5:[2,9],6:[2,9],12:51,27:[2,9],32:[2,9],44:[2,9],45:[1,18]},{46:[1,52]},{5:[2,46],6:[2,46],27:[2,46],32:[2,46],44:[2,46],45:[2,46],52:53,55:[1,54]},{46:[1,55]},{5:[2,13],6:[2,13],27:[2,13],32:[2,13],44:[2,13],45:[2,13],53:[2,13]},{23:57,25:[1,28],30:34,37:56,49:29,62:[1,30],63:31,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],76:[1,44],78:[1,39]},{19:[1,58],42:[1,59]},{18:60,23:33,25:[1,28],30:34,37:26,49:29,62:[1,30],63:31,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],74:27,75:[1,32],76:[1,44],78:[1,39],86:25},{19:[2,91],42:[2,91]},{19:[2,93],23:61,24:[1,62],42:[2,93],56:[1,63],57:[1,64],58:[1,65],59:[1,66],72:[1,67]},{19:[2,96],42:[2,96]},{23:57,25:[1,28],30:34,37:68,49:29,62:[1,30],63:31,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],76:[1,44],78:[1,39]},{5:[2,58],6:[2,58],19:[2,58],24:[2,58],27:[2,58],32:[2,58],35:[2,58],38:[2,58],39:[2,58],42:[2,58],43:[2,58],44:[2,58],45:[2,58],53:[2,58],55:[2,58],56:[2,58],57:[2,58],58:[2,58],59:[2,58],60:[1,69],72:[2,58],81:[2,58],83:[2,58],84:[2,58],85:[2,58]},{25:[1,71],61:70},{5:[2,59],6:[2,59],19:[2,59],24:[2,59],27:[2,59],32:[2,59],35:[2,59],38:[2,59],39:[2,59],42:[2,59],43:[2,59],44:[2,59],45:[2,59],53:[2,59],55:[2,59],56:[2,59],57:[2,59],58:[2,59],59:[2,59],72:[2,59],81:[2,59],83:[2,59],84:[2,59],85:[2,59]},{19:[2,74],27:[2,74],42:[2,74]},{19:[2,61],24:[2,61],27:[2,61],42:[2,61],56:[2,61],57:[2,61],58:[2,61],59:[2,61],60:[2,61],72:[2,61],73:[1,72]},{5:[2,62],6:[2,62],19:[2,62],24:[2,62],27:[2,62],32:[2,62],35:[2,62],38:[2,62],39:[2,62],42:[2,62],43:[2,62],44:[2,62],45:[2,62],50:[2,62],53:[2,62],55:[2,62],56:[2,62],57:[2,62],58:[2,62],59:[2,62],60:[2,62],72:[2,62],81:[2,62],83:[2,62],84:[2,62],85:[2,62]},{5:[2,63],6:[2,63],19:[2,63],24:[2,63],27:[2,63],32:[2,63],35:[2,63],38:[2,63],39:[2,63],42:[2,63],43:[2,63],44:[2,63],45:[2,63],50:[2,63],53:[2,63],55:[2,63],56:[2,63],57:[2,63],58:[2,63],59:[2,63],60:[2,63],72:[2,63],81:[2,63],83:[2,63],84:[2,63],85:[2,63]},{5:[2,64],6:[2,64],19:[2,64],24:[2,64],27:[2,64],32:[2,64],35:[2,64],38:[2,64],39:[2,64],42:[2,64],43:[2,64],44:[2,64],45:[2,64],50:[2,64],53:[2,64],55:[2,64],56:[2,64],57:[2,64],58:[2,64],59:[2,64],60:[2,64],72:[2,64],81:[2,64],83:[2,64],84:[2,64],85:[2,64]},{5:[2,65],6:[2,65],19:[2,65],24:[2,65],27:[2,65],32:[2,65],35:[2,65],38:[2,65],39:[2,65],42:[2,65],43:[2,65],44:[2,65],45:[2,65],50:[2,65],53:[2,65],55:[2,65],56:[2,65],57:[2,65],58:[2,65],59:[2,65],60:[2,65],72:[2,65],81:[2,65],83:[2,65],84:[2,65],85:[2,65]},{5:[2,66],6:[2,66],19:[2,66],24:[2,66],27:[2,66],32:[2,66],35:[2,66],38:[2,66],39:[2,66],42:[2,66],43:[2,66],44:[2,66],45:[2,66],50:[2,66],53:[2,66],55:[2,66],56:[2,66],57:[2,66],58:[2,66],59:[2,66],60:[2,66],72:[2,66],81:[2,66],83:[2,66],84:[2,66],85:[2,66]},{23:57,25:[1,28],30:34,37:73,49:29,62:[1,30],63:31,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],76:[1,44],78:[1,39],79:74,82:75,83:[1,76]},{5:[2,72],6:[2,72],19:[2,72],24:[2,72],25:[1,77],27:[2,72],32:[2,72],35:[2,72],38:[2,72],39:[2,72],42:[2,72],43:[2,72],44:[2,72],45:[2,72],50:[2,72],53:[2,72],55:[2,72],56:[2,72],57:[2,72],58:[2,72],59:[2,72],60:[2,72],72:[2,72],73:[2,72],81:[2,72],83:[2,72],84:[2,72],85:[2,72]},{5:[2,68],6:[2,68],19:[2,68],24:[2,68],27:[2,68],32:[2,68],35:[2,68],38:[2,68],39:[2,68],42:[2,68],43:[2,68],44:[2,68],45:[2,68],50:[2,68],53:[2,68],55:[2,68],56:[2,68],57:[2,68],58:[2,68],59:[2,68],60:[2,68],72:[2,68],81:[2,68],83:[2,68],84:[2,68],85:[2,68]},{5:[2,70],6:[2,70],19:[2,70],24:[2,70],27:[2,70],32:[2,70],35:[2,70],38:[2,70],39:[2,70],42:[2,70],43:[2,70],44:[2,70],45:[2,70],50:[2,70],53:[2,70],55:[2,70],56:[2,70],57:[2,70],58:[2,70],59:[2,70],60:[2,70],72:[2,70],81:[2,70],83:[2,70],84:[2,70],85:[2,70]},{5:[2,71],6:[2,71],19:[2,71],24:[2,71],27:[2,71],32:[2,71],35:[2,71],38:[2,71],39:[2,71],42:[2,71],43:[2,71],44:[2,71],45:[2,71],50:[2,71],53:[2,71],55:[2,71],56:[2,71],57:[2,71],58:[2,71],59:[2,71],60:[2,71],72:[2,71],81:[2,71],83:[2,71],84:[2,71],85:[2,71]},{25:[1,78]},{5:[2,69],6:[2,69],19:[2,69],24:[2,69],27:[2,69],32:[2,69],35:[2,69],38:[2,69],39:[2,69],42:[2,69],43:[2,69],44:[2,69],45:[2,69],50:[2,69],53:[2,69],55:[2,69],56:[2,69],57:[2,69],58:[2,69],59:[2,69],60:[2,69],72:[2,69],81:[2,69],83:[2,69],84:[2,69],85:[2,69]},{1:[2,2]},{5:[2,26],6:[2,26],27:[2,26],32:[2,26]},{5:[2,40],6:[2,40],27:[2,40],32:[2,40],44:[2,40]},{5:[2,27],6:[2,27],14:12,27:[2,27],32:[2,27],44:[1,14]},{7:79,9:4,10:5,11:6,15:7,17:[1,8]},{5:[2,10],6:[2,10],27:[2,10],32:[2,10],44:[2,10]},{23:57,30:34,47:80,48:81,49:82,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],76:[1,44]},{5:[2,47],6:[2,47],27:[2,47],32:[2,47],44:[2,47],45:[2,47]},{23:57,25:[1,28],30:34,37:83,49:29,62:[1,30],63:31,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],76:[1,44],78:[1,39]},{23:57,25:[1,28],30:34,37:85,49:29,54:84,62:[1,30],63:31,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],76:[1,44],78:[1,39]},{5:[2,39],6:[2,39],27:[2,39],32:[2,39],44:[2,39],45:[2,39],53:[2,39],56:[1,63],57:[1,64],58:[1,65],59:[1,66]},{5:[2,61],6:[2,61],19:[2,61],24:[2,61],27:[2,61],32:[2,61],35:[2,61],38:[2,61],39:[2,61],42:[2,61],43:[2,61],44:[2,61],45:[2,61],50:[2,61],53:[2,61],55:[2,61],56:[2,61],57:[2,61],58:[2,61],59:[2,61],60:[2,61],72:[2,61],73:[1,86],81:[2,61],83:[2,61],84:[2,61],85:[2,61]},{20:87,23:88,25:[1,89],72:[1,67]},{23:33,25:[1,28],30:34,37:26,49:29,62:[1,30],63:31,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],74:27,75:[1,32],76:[1,44],78:[1,39],86:90},{19:[1,91],42:[1,59]},{19:[2,94],42:[2,94],73:[1,86]},{23:92,72:[1,67]},{23:57,25:[1,28],30:34,37:93,49:29,62:[1,30],63:31,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],76:[1,44],78:[1,39]},{23:57,25:[1,28],30:34,37:94,49:29,62:[1,30],63:31,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],76:[1,44],78:[1,39]},{23:57,25:[1,28],30:34,37:95,49:29,62:[1,30],63:31,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],76:[1,44],78:[1,39]},{23:57,25:[1,28],30:34,37:96,49:29,62:[1,30],63:31,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],76:[1,44],78:[1,39]},{5:[2,72],6:[2,72],19:[2,72],24:[2,72],27:[2,72],28:[2,72],32:[2,72],35:[2,72],36:[2,72],38:[2,72],39:[2,72],42:[2,72],43:[2,72],44:[2,72],45:[2,72],53:[2,72],72:[2,72],73:[2,72]},{27:[1,97],56:[1,63],57:[1,64],58:[1,65],59:[1,66]},{25:[1,98],61:99},{5:[2,57],6:[2,57],19:[2,57],24:[2,57],27:[2,57],32:[2,57],35:[2,57],38:[2,57],39:[2,57],42:[2,57],43:[2,57],44:[2,57],45:[2,57],53:[2,57],55:[2,57],56:[2,57],57:[2,57],58:[2,57],59:[2,57],72:[2,57],81:[2,57],83:[2,57],84:[2,57],85:[2,57]},{4:100,7:3,9:4,10:5,11:6,15:7,17:[1,8]},{72:[1,102],75:[1,101]},{56:[1,63],57:[1,64],58:[1,65],59:[1,66],79:103,82:75,83:[1,76]},{80:104,81:[1,105],82:106,83:[1,76],85:[1,107]},{81:[2,88],83:[2,88],85:[2,88]},{23:57,25:[1,28],30:34,37:108,49:29,62:[1,30],63:31,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],76:[1,44],78:[1,39]},{21:[1,112],23:33,25:[1,28],30:34,37:85,49:29,54:111,62:[1,30],63:31,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],74:110,75:[1,32],76:[1,44],77:109,78:[1,39]},{21:[1,112],23:33,25:[1,28],30:34,37:85,49:29,54:111,62:[1,30],63:31,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],74:110,75:[1,32],76:[1,44],77:113,78:[1,39]},{5:[2,28],6:[2,28],14:12,27:[2,28],32:[2,28],44:[1,14]},{5:[2,41],6:[2,41],27:[2,41],32:[2,41],42:[1,114],44:[2,41]},{5:[2,42],6:[2,42],27:[2,42],32:[2,42],42:[2,42],44:[2,42]},{5:[2,44],6:[2,44],27:[2,44],32:[2,44],42:[2,44],44:[2,44],50:[1,115]},{5:[2,49],6:[2,49],27:[2,49],32:[2,49],44:[2,49],45:[2,49],56:[1,63],57:[1,64],58:[1,65],59:[1,66]},{5:[2,48],6:[2,48],27:[2,48],32:[2,48],42:[1,116],44:[2,48],45:[2,48],55:[2,48]},{5:[2,81],6:[2,81],27:[2,81],32:[2,81],42:[2,81],44:[2,81],45:[2,81],55:[2,81],56:[1,63],57:[1,64],58:[1,65],59:[1,66]},{72:[1,102]},{5:[2,14],6:[2,14],22:117,27:[2,14],32:[2,14],34:118,35:[1,119],38:[1,120],39:[1,121],42:[1,122],43:[2,14],44:[2,14],45:[2,14],53:[2,14]},{5:[2,18],6:[2,18],23:123,24:[1,124],27:[2,18],28:[1,125],32:[2,18],35:[2,18],36:[2,18],38:[2,18],39:[2,18],42:[2,18],43:[2,18],44:[2,18],45:[2,18],53:[2,18],72:[1,67],73:[1,86]},{4:127,7:3,9:4,10:5,11:6,15:7,17:[1,8],23:57,25:[1,28],26:126,30:34,37:85,49:29,54:128,62:[1,30],63:31,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],76:[1,44],78:[1,39]},{19:[2,92],42:[2,92]},{20:129,23:88,25:[1,89],72:[1,67]},{19:[2,95],42:[2,95],73:[1,86]},{5:[2,51],6:[2,51],19:[2,51],24:[2,51],27:[2,51],32:[2,51],35:[2,51],38:[2,51],39:[2,51],42:[2,51],43:[2,51],44:[2,51],45:[2,51],53:[2,51],55:[2,51],56:[2,51],57:[1,64],58:[2,51],59:[2,51],72:[2,51],81:[2,51],83:[2,51],84:[2,51],85:[2,51]},{5:[2,52],6:[2,52],19:[2,52],24:[2,52],27:[2,52],32:[2,52],35:[2,52],38:[2,52],39:[2,52],42:[2,52],43:[2,52],44:[2,52],45:[2,52],53:[2,52],55:[2,52],56:[2,52],57:[2,52],58:[2,52],59:[2,52],72:[2,52],81:[2,52],83:[2,52],84:[2,52],85:[2,52]},{5:[2,53],6:[2,53],19:[2,53],24:[2,53],27:[2,53],32:[2,53],35:[2,53],38:[2,53],39:[2,53],42:[2,53],43:[2,53],44:[2,53],45:[2,53],53:[2,53],55:[2,53],56:[1,63],57:[1,64],58:[2,53],59:[2,53],72:[2,53],81:[2,53],83:[2,53],84:[2,53],85:[2,53]},{5:[2,54],6:[2,54],19:[2,54],24:[2,54],27:[2,54],32:[2,54],35:[2,54],38:[2,54],39:[2,54],42:[2,54],43:[2,54],44:[2,54],45:[2,54],53:[2,54],55:[2,54],56:[1,63],57:[1,64],58:[1,65],59:[2,54],72:[2,54],81:[2,54],83:[2,54],84:[2,54],85:[2,54]},{5:[2,50],6:[2,50],19:[2,50],24:[2,50],27:[2,50],32:[2,50],35:[2,50],38:[2,50],39:[2,50],42:[2,50],43:[2,50],44:[2,50],45:[2,50],53:[2,50],55:[2,50],56:[2,50],57:[2,50],58:[2,50],59:[2,50],72:[2,50],81:[2,50],83:[2,50],84:[2,50],85:[2,50]},{4:100,7:3,9:4,10:5,11:6,15:7,17:[1,8],23:57,25:[1,28],26:130,30:34,37:85,49:29,54:128,62:[1,30],63:31,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],76:[1,44],78:[1,39]},{5:[2,56],6:[2,56],19:[2,56],24:[2,56],27:[2,56],32:[2,56],35:[2,56],38:[2,56],39:[2,56],42:[2,56],43:[2,56],44:[2,56],45:[2,56],53:[2,56],55:[2,56],56:[2,56],57:[2,56],58:[2,56],59:[2,56],72:[2,56],81:[2,56],83:[2,56],84:[2,56],85:[2,56]},{27:[1,131]},{19:[2,75],27:[2,75],42:[2,75]},{5:[2,73],6:[2,73],19:[2,73],24:[2,73],27:[2,73],28:[2,73],32:[2,73],35:[2,73],36:[2,73],38:[2,73],39:[2,73],42:[2,73],43:[2,73],44:[2,73],45:[2,73],50:[2,73],53:[2,73],55:[2,73],56:[2,73],57:[2,73],58:[2,73],59:[2,73],60:[2,73],72:[2,73],73:[2,73],81:[2,73],83:[2,73],84:[2,73],85:[2,73]},{80:132,81:[1,133],82:106,83:[1,76],85:[1,107]},{81:[1,134]},{5:[2,86],6:[2,86],19:[2,86],24:[2,86],27:[2,86],32:[2,86],35:[2,86],38:[2,86],39:[2,86],42:[2,86],43:[2,86],44:[2,86],45:[2,86],53:[2,86],55:[2,86],56:[2,86],57:[2,86],58:[2,86],59:[2,86],72:[2,86],81:[2,86],83:[2,86],84:[2,86],85:[2,86]},{81:[2,87],83:[2,87],85:[2,87]},{23:57,25:[1,28],30:34,37:135,49:29,62:[1,30],63:31,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],76:[1,44],78:[1,39]},{56:[1,63],57:[1,64],58:[1,65],59:[1,66],84:[1,136]},{27:[1,137]},{27:[2,78]},{27:[2,79],42:[1,116]},{23:57,25:[1,28],30:34,37:85,49:29,54:138,62:[1,30],63:31,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],76:[1,44],78:[1,39]},{27:[1,139]},{23:57,30:34,48:140,49:82,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],76:[1,44]},{5:[2,45],6:[2,45],27:[2,45],32:[2,45],42:[2,45],44:[2,45]},{23:57,30:34,49:141,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],76:[1,44]},{5:[2,16],6:[2,16],27:[2,16],32:[2,16],34:142,35:[1,119],38:[1,120],39:[1,121],42:[1,122],43:[2,16],44:[2,16],45:[2,16],53:[2,16]},{5:[2,29],6:[2,29],27:[2,29],32:[2,29],35:[2,29],38:[2,29],39:[2,29],42:[2,29],43:[2,29],44:[2,29],45:[2,29],53:[2,29]},{20:143,23:88,25:[1,89],72:[1,67]},{35:[1,144],40:[1,145],41:[1,146]},{35:[1,147],40:[1,148],41:[1,149]},{20:150,23:88,25:[1,89],72:[1,67]},{5:[2,19],6:[2,19],27:[2,19],32:[2,19],35:[2,19],36:[2,19],38:[2,19],39:[2,19],42:[2,19],43:[2,19],44:[2,19],45:[2,19],53:[2,19],73:[1,86]},{23:151,72:[1,67]},{29:[1,152]},{27:[1,153]},{27:[1,154]},{27:[2,67],42:[1,116]},{5:[2,15],6:[2,15],22:155,27:[2,15],32:[2,15],34:118,35:[1,119],38:[1,120],39:[1,121],42:[1,122],43:[2,15],44:[2,15],45:[2,15],53:[2,15]},{27:[1,156]},{5:[2,60],6:[2,60],19:[2,60],24:[2,60],27:[2,60],32:[2,60],35:[2,60],38:[2,60],39:[2,60],42:[2,60],43:[2,60],44:[2,60],45:[2,60],53:[2,60],55:[2,60],56:[2,60],57:[2,60],58:[2,60],59:[2,60],72:[2,60],81:[2,60],83:[2,60],84:[2,60],85:[2,60]},{81:[1,157]},{5:[2,84],6:[2,84],19:[2,84],24:[2,84],27:[2,84],32:[2,84],35:[2,84],38:[2,84],39:[2,84],42:[2,84],43:[2,84],44:[2,84],45:[2,84],53:[2,84],55:[2,84],56:[2,84],57:[2,84],58:[2,84],59:[2,84],72:[2,84],81:[2,84],83:[2,84],84:[2,84],85:[2,84]},{5:[2,85],6:[2,85],19:[2,85],24:[2,85],27:[2,85],32:[2,85],35:[2,85],38:[2,85],39:[2,85],42:[2,85],43:[2,85],44:[2,85],45:[2,85],53:[2,85],55:[2,85],56:[2,85],57:[2,85],58:[2,85],59:[2,85],72:[2,85],81:[2,85],83:[2,85],84:[2,85],85:[2,85]},{56:[1,63],57:[1,64],58:[1,65],59:[1,66],81:[2,90]},{23:57,25:[1,28],30:34,37:158,49:29,62:[1,30],63:31,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],76:[1,44],78:[1,39]},{5:[2,77],6:[2,77],19:[2,77],24:[2,77],27:[2,77],32:[2,77],35:[2,77],38:[2,77],39:[2,77],42:[2,77],43:[2,77],44:[2,77],45:[2,77],50:[2,77],53:[2,77],55:[2,77],56:[2,77],57:[2,77],58:[2,77],59:[2,77],60:[2,77],72:[2,77],81:[2,77],83:[2,77],84:[2,77],85:[2,77]},{27:[2,80],42:[1,116]},{5:[2,76],6:[2,76],19:[2,76],24:[2,76],27:[2,76],32:[2,76],35:[2,76],38:[2,76],39:[2,76],42:[2,76],43:[2,76],44:[2,76],45:[2,76],50:[2,76],53:[2,76],55:[2,76],56:[2,76],57:[2,76],58:[2,76],59:[2,76],60:[2,76],72:[2,76],81:[2,76],83:[2,76],84:[2,76],85:[2,76]},{5:[2,43],6:[2,43],27:[2,43],32:[2,43],42:[2,43],44:[2,43]},{5:[2,82],6:[2,82],27:[2,82],32:[2,82],42:[2,82],44:[2,82],45:[2,82],55:[2,82]},{5:[2,30],6:[2,30],27:[2,30],32:[2,30],35:[2,30],38:[2,30],39:[2,30],42:[2,30],43:[2,30],44:[2,30],45:[2,30],53:[2,30]},{36:[1,159]},{20:160,23:88,25:[1,89],72:[1,67]},{35:[1,161]},{35:[1,162]},{20:163,23:88,25:[1,89],72:[1,67]},{35:[1,164]},{35:[1,165]},{5:[2,38],6:[2,38],27:[2,38],32:[2,38],35:[2,38],38:[2,38],39:[2,38],42:[2,38],43:[2,38],44:[2,38],45:[2,38],53:[2,38]},{5:[2,20],6:[2,20],27:[2,20],32:[2,20],35:[2,20],36:[2,20],38:[2,20],39:[2,20],42:[2,20],43:[2,20],44:[2,20],45:[2,20],53:[2,20],73:[1,86]},{25:[1,166]},{5:[2,21],6:[2,21],27:[2,21],32:[2,21],35:[2,21],36:[2,21],38:[2,21],39:[2,21],42:[2,21],43:[2,21],44:[2,21],45:[2,21],53:[2,21]},{5:[2,22],6:[2,22],23:167,27:[2,22],32:[2,22],35:[2,22],36:[2,22],38:[2,22],39:[2,22],42:[2,22],43:[2,22],44:[2,22],45:[2,22],53:[2,22],72:[1,67]},{5:[2,17],6:[2,17],27:[2,17],32:[2,17],34:142,35:[1,119],38:[1,120],39:[1,121],42:[1,122],43:[2,17],44:[2,17],45:[2,17],53:[2,17]},{5:[2,55],6:[2,55],19:[2,55],24:[2,55],27:[2,55],32:[2,55],35:[2,55],38:[2,55],39:[2,55],42:[2,55],43:[2,55],44:[2,55],45:[2,55],53:[2,55],55:[2,55],56:[2,55],57:[2,55],58:[2,55],59:[2,55],72:[2,55],81:[2,55],83:[2,55],84:[2,55],85:[2,55]},{5:[2,83],6:[2,83],19:[2,83],24:[2,83],27:[2,83],32:[2,83],35:[2,83],38:[2,83],39:[2,83],42:[2,83],43:[2,83],44:[2,83],45:[2,83],53:[2,83],55:[2,83],56:[2,83],57:[2,83],58:[2,83],59:[2,83],72:[2,83],81:[2,83],83:[2,83],84:[2,83],85:[2,83]},{56:[1,63],57:[1,64],58:[1,65],59:[1,66],81:[2,89],83:[2,89],85:[2,89]},{23:57,25:[1,28],30:34,37:168,49:29,62:[1,30],63:31,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],76:[1,44],78:[1,39]},{36:[1,169]},{20:170,23:88,25:[1,89],72:[1,67]},{20:171,23:88,25:[1,89],72:[1,67]},{36:[1,172]},{20:173,23:88,25:[1,89],72:[1,67]},{20:174,23:88,25:[1,89],72:[1,67]},{30:175,68:[1,41]},{5:[2,23],6:[2,23],27:[2,23],32:[2,23],35:[2,23],36:[2,23],38:[2,23],39:[2,23],42:[2,23],43:[2,23],44:[2,23],45:[2,23],53:[2,23],73:[1,86]},{5:[2,31],6:[2,31],27:[2,31],32:[2,31],35:[2,31],38:[2,31],39:[2,31],42:[2,31],43:[2,31],44:[2,31],45:[2,31],53:[2,31],56:[1,63],57:[1,64],58:[1,65],59:[1,66]},{23:57,25:[1,28],30:34,37:176,49:29,62:[1,30],63:31,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],76:[1,44],78:[1,39]},{36:[1,177]},{36:[1,178]},{23:57,25:[1,28],30:34,37:179,49:29,62:[1,30],63:31,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],76:[1,44],78:[1,39]},{36:[1,180]},{36:[1,181]},{27:[1,182]},{5:[2,32],6:[2,32],27:[2,32],32:[2,32],35:[2,32],38:[2,32],39:[2,32],42:[2,32],43:[2,32],44:[2,32],45:[2,32],53:[2,32],56:[1,63],57:[1,64],58:[1,65],59:[1,66]},{23:57,25:[1,28],30:34,37:183,49:29,62:[1,30],63:31,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],76:[1,44],78:[1,39]},{23:57,25:[1,28],30:34,37:184,49:29,62:[1,30],63:31,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],76:[1,44],78:[1,39]},{5:[2,33],6:[2,33],27:[2,33],32:[2,33],35:[2,33],38:[2,33],39:[2,33],42:[2,33],43:[2,33],44:[2,33],45:[2,33],53:[2,33],56:[1,63],57:[1,64],58:[1,65],59:[1,66]},{23:57,25:[1,28],30:34,37:185,49:29,62:[1,30],63:31,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],76:[1,44],78:[1,39]},{23:57,25:[1,28],30:34,37:186,49:29,62:[1,30],63:31,64:35,65:36,66:37,67:38,68:[1,41],69:[1,45],70:[1,42],71:[1,43],72:[1,40],76:[1,44],78:[1,39]},{5:[2,24],6:[2,24],27:[2,24],32:[2,24],35:[2,24],36:[2,24],38:[2,24],39:[2,24],42:[2,24],43:[2,24],44:[2,24],45:[2,24],53:[2,24]},{5:[2,34],6:[2,34],27:[2,34],32:[2,34],35:[2,34],38:[2,34],39:[2,34],42:[2,34],43:[2,34],44:[2,34],45:[2,34],53:[2,34],56:[1,63],57:[1,64],58:[1,65],59:[1,66]},{5:[2,36],6:[2,36],27:[2,36],32:[2,36],35:[2,36],38:[2,36],39:[2,36],42:[2,36],43:[2,36],44:[2,36],45:[2,36],53:[2,36],56:[1,63],57:[1,64],58:[1,65],59:[1,66]},{5:[2,35],6:[2,35],27:[2,35],32:[2,35],35:[2,35],38:[2,35],39:[2,35],42:[2,35],43:[2,35],44:[2,35],45:[2,35],53:[2,35],56:[1,63],57:[1,64],58:[1,65],59:[1,66]},{5:[2,37],6:[2,37],27:[2,37],32:[2,37],35:[2,37],38:[2,37],39:[2,37],42:[2,37],43:[2,37],44:[2,37],45:[2,37],53:[2,37],56:[1,63],57:[1,64],58:[1,65],59:[1,66]}],
defaultActions: {9:[2,1],46:[2,2],110:[2,78]},
parseError: function parseError(str, hash) {
    if (hash.recoverable) {
        this.trace(str);
    } else {
        throw new Error(str);
    }
},
parse: function parse(input) {
    var self = this, stack = [0], vstack = [null], lstack = [], table = this.table, yytext = '', yylineno = 0, yyleng = 0, recovering = 0, TERROR = 2, EOF = 1;
    var args = lstack.slice.call(arguments, 1);
    this.lexer.setInput(input);
    this.lexer.yy = this.yy;
    this.yy.lexer = this.lexer;
    this.yy.parser = this;
    if (typeof this.lexer.yylloc == 'undefined') {
        this.lexer.yylloc = {};
    }
    var yyloc = this.lexer.yylloc;
    lstack.push(yyloc);
    var ranges = this.lexer.options && this.lexer.options.ranges;
    if (typeof this.yy.parseError === 'function') {
        this.parseError = this.yy.parseError;
    } else {
        this.parseError = Object.getPrototypeOf(this).parseError;
    }
    function popStack(n) {
        stack.length = stack.length - 2 * n;
        vstack.length = vstack.length - n;
        lstack.length = lstack.length - n;
    }
    function lex() {
        var token;
        token = self.lexer.lex() || EOF;
        if (typeof token !== 'number') {
            token = self.symbols_[token] || token;
        }
        return token;
    }
    var symbol, preErrorSymbol, state, action, a, r, yyval = {}, p, len, newState, expected;
    while (true) {
        state = stack[stack.length - 1];
        if (this.defaultActions[state]) {
            action = this.defaultActions[state];
        } else {
            if (symbol === null || typeof symbol == 'undefined') {
                symbol = lex();
            }
            action = table[state] && table[state][symbol];
        }
                    if (typeof action === 'undefined' || !action.length || !action[0]) {
                var errStr = '';
                expected = [];
                for (p in table[state]) {
                    if (this.terminals_[p] && p > TERROR) {
                        expected.push('\'' + this.terminals_[p] + '\'');
                    }
                }
                if (this.lexer.showPosition) {
                    errStr = 'Parse error on line ' + (yylineno + 1) + ':\n' + this.lexer.showPosition() + '\nExpecting ' + expected.join(', ') + ', got \'' + (this.terminals_[symbol] || symbol) + '\'';
                } else {
                    errStr = 'Parse error on line ' + (yylineno + 1) + ': Unexpected ' + (symbol == EOF ? 'end of input' : '\'' + (this.terminals_[symbol] || symbol) + '\'');
                }
                this.parseError(errStr, {
                    text: this.lexer.match,
                    token: this.terminals_[symbol] || symbol,
                    line: this.lexer.yylineno,
                    loc: yyloc,
                    expected: expected
                });
            }
        if (action[0] instanceof Array && action.length > 1) {
            throw new Error('Parse Error: multiple actions possible at state: ' + state + ', token: ' + symbol);
        }
        switch (action[0]) {
        case 1:
            stack.push(symbol);
            vstack.push(this.lexer.yytext);
            lstack.push(this.lexer.yylloc);
            stack.push(action[1]);
            symbol = null;
            if (!preErrorSymbol) {
                yyleng = this.lexer.yyleng;
                yytext = this.lexer.yytext;
                yylineno = this.lexer.yylineno;
                yyloc = this.lexer.yylloc;
                if (recovering > 0) {
                    recovering--;
                }
            } else {
                symbol = preErrorSymbol;
                preErrorSymbol = null;
            }
            break;
        case 2:
            len = this.productions_[action[1]][1];
            yyval.$ = vstack[vstack.length - len];
            yyval._$ = {
                first_line: lstack[lstack.length - (len || 1)].first_line,
                last_line: lstack[lstack.length - 1].last_line,
                first_column: lstack[lstack.length - (len || 1)].first_column,
                last_column: lstack[lstack.length - 1].last_column
            };
            if (ranges) {
                yyval._$.range = [
                    lstack[lstack.length - (len || 1)].range[0],
                    lstack[lstack.length - 1].range[1]
                ];
            }
            r = this.performAction.apply(yyval, [
                yytext,
                yyleng,
                yylineno,
                this.yy,
                action[1],
                vstack,
                lstack
            ].concat(args));
            if (typeof r !== 'undefined') {
                return r;
            }
            if (len) {
                stack = stack.slice(0, -1 * len * 2);
                vstack = vstack.slice(0, -1 * len);
                lstack = lstack.slice(0, -1 * len);
            }
            stack.push(this.productions_[action[1]][0]);
            vstack.push(yyval.$);
            lstack.push(yyval._$);
            newState = table[stack[stack.length - 2]][stack[stack.length - 1]];
            stack.push(newState);
            break;
        case 3:
            return true;
        }
    }
    return true;
}};

function Parser () {
  this.yy = {};
}
Parser.prototype = parser;parser.Parser = Parser;
return new Parser;
})();


if (typeof require !== 'undefined' && typeof exports !== 'undefined') {
exports.parser = parser;
exports.Parser = parser.Parser;
exports.parse = function () { return parser.parse.apply(parser, arguments); };
exports.main = function commonjsMain(args) {
    if (!args[1]) {
        console.log('Usage: '+args[0]+' FILE');
        process.exit(1);
    }
    var source = require('fs').readFileSync(require('path').normalize(args[1]), "utf8");
    return exports.parser.parse(source);
};
if (typeof module !== 'undefined' && require.main === module) {
  exports.main(process.argv.slice(1));
}
}
};require['./nodes'] = new function() {
  var exports = this;
  (function() {
  var ArgumentListValue, Field, FunctionValue, Group, Having, Join, Limit, ListValue, LiteralValue, Op, Order, OrderArgument, Select, Star, StringValue, SubSelect, Table, UnaryOp, Union, Where, indent;

  indent = function(str) {
    var line;
    return ((function() {
      var _i, _len, _ref, _results;
      _ref = str.split("\n");
      _results = [];
      for (_i = 0, _len = _ref.length; _i < _len; _i++) {
        line = _ref[_i];
        _results.push("  " + line);
      }
      return _results;
    })()).join("\n");
  };

  exports.Select = Select = (function() {

    function Select(fields, source, distinct, joins, unions) {
      this.fields = fields;
      this.source = source;
      this.distinct = distinct != null ? distinct : false;
      this.joins = joins != null ? joins : [];
      this.unions = unions != null ? unions : [];
      this.order = null;
      this.group = null;
      this.where = null;
      this.limit = null;
    }

    Select.prototype.toString = function() {
      var join, ret, union, _i, _j, _len, _len2, _ref, _ref2;
      ret = ["SELECT " + (this.fields.join(', '))];
      ret.push(indent("FROM " + this.source));
      _ref = this.joins;
      for (_i = 0, _len = _ref.length; _i < _len; _i++) {
        join = _ref[_i];
        ret.push(indent(join.toString()));
      }
      if (this.where) ret.push(indent(this.where.toString()));
      if (this.group) ret.push(indent(this.group.toString()));
      if (this.order) ret.push(indent(this.order.toString()));
      if (this.limit) ret.push(indent(this.limit.toString()));
      _ref2 = this.unions;
      for (_j = 0, _len2 = _ref2.length; _j < _len2; _j++) {
        union = _ref2[_j];
        ret.push(union.toString());
      }
      return ret.join("\n");
    };

    return Select;

  })();

  exports.SubSelect = SubSelect = (function() {

    function SubSelect(select, name) {
      this.select = select;
      this.name = name != null ? name : null;
      null;
    }

    SubSelect.prototype.toString = function() {
      var ret;
      ret = [];
      ret.push('(');
      ret.push(indent(this.select.toString()));
      ret.push(this.name ? ") " + (this.name.toString()) : ")");
      return ret.join("\n");
    };

    return SubSelect;

  })();

  exports.Join = Join = (function() {

    function Join(right, conditions, side, mode) {
      this.right = right;
      this.conditions = conditions != null ? conditions : null;
      this.side = side != null ? side : null;
      this.mode = mode != null ? mode : null;
      null;
    }

    Join.prototype.toString = function() {
      var ret;
      ret = '';
      if (this.side != null) ret += "" + this.side + " ";
      if (this.mode != null) ret += "" + this.mode + " ";
      ret += "JOIN " + this.right + "\n";
      if (this.conditions != null) return ret += indent("ON " + this.conditions);
    };

    return Join;

  })();

  exports.Union = Union = (function() {

    function Union(query, all) {
      this.query = query;
      this.all = all != null ? all : false;
      null;
    }

    Union.prototype.toString = function() {
      var all;
      all = this.all ? ' ALL' : '';
      return "UNION" + all + "\n" + (this.query.toString());
    };

    return Union;

  })();

  exports.LiteralValue = LiteralValue = (function() {

    function LiteralValue(value, value2) {
      this.value = value;
      this.value2 = value2 != null ? value2 : null;
      if (this.value2) {
        this.nested = true;
        this.values = this.value.values;
        this.values.push(value2);
      } else {
        this.nested = false;
        this.values = [this.value];
      }
    }

    LiteralValue.prototype.toString = function() {
      return "`" + (this.values.join('.')) + "`";
    };

    return LiteralValue;

  })();

  exports.StringValue = StringValue = (function() {

    function StringValue(value, quoteType) {
      this.value = value;
      this.quoteType = quoteType != null ? quoteType : "''";
      null;
    }

    StringValue.prototype.toString = function() {
      return "" + this.quoteType + this.value + this.quoteType;
    };

    return StringValue;

  })();

  exports.NumberValue = LiteralValue = (function() {

    function LiteralValue(value) {
      this.value = Number(value);
    }

    LiteralValue.prototype.toString = function() {
      return this.value.toString();
    };

    return LiteralValue;

  })();

  exports.ListValue = ListValue = (function() {

    function ListValue(value) {
      this.value = value;
    }

    ListValue.prototype.toString = function() {
      return "(" + (this.value.join(', ')) + ")";
    };

    return ListValue;

  })();

  exports.ArgumentListValue = ArgumentListValue = (function() {

    function ArgumentListValue(value, distinct) {
      this.value = value;
      this.distinct = distinct != null ? distinct : false;
      null;
    }

    ArgumentListValue.prototype.toString = function() {
      if (this.distinct) {
        return "DISTINCT " + (this.value.join(', '));
      } else {
        return "" + (this.value.join(', '));
      }
    };

    return ArgumentListValue;

  })();

  exports.BooleanValue = LiteralValue = (function() {

    function LiteralValue(value) {
      this.value = (function() {
        switch (value.toLowerCase()) {
          case 'true':
            return true;
          case 'false':
            return false;
          default:
            return null;
        }
      })();
    }

    LiteralValue.prototype.toString = function() {
      if (this.value != null) {
        return this.value.toString().toUpperCase();
      } else {
        return 'NULL';
      }
    };

    return LiteralValue;

  })();

  exports.FunctionValue = FunctionValue = (function() {

    function FunctionValue(name, _arguments, udf) {
      this.name = name;
      this.arguments = _arguments != null ? _arguments : null;
      this.udf = udf != null ? udf : false;
      null;
    }

    FunctionValue.prototype.toString = function() {
      if (this.arguments) {
        return "" + (this.name.toUpperCase()) + "(" + (this.arguments.toString()) + ")";
      } else {
        return "" + (this.name.toUpperCase()) + "()";
      }
    };

    return FunctionValue;

  })();

  exports.Order = Order = (function() {

    function Order(orderings) {
      this.orderings = orderings;
    }

    Order.prototype.toString = function() {
      return "ORDER BY " + (this.orderings.join(', '));
    };

    return Order;

  })();

  exports.OrderArgument = OrderArgument = (function() {

    function OrderArgument(value, direction) {
      this.value = value;
      this.direction = direction != null ? direction : 'ASC';
      null;
    }

    OrderArgument.prototype.toString = function() {
      return "" + this.value + " " + this.direction;
    };

    return OrderArgument;

  })();

  exports.Limit = Limit = (function() {

    function Limit(value) {
      this.value = value;
      null;
    }

    Limit.prototype.toString = function() {
      return "LIMIT " + this.value;
    };

    return Limit;

  })();

  exports.Table = Table = (function() {

    function Table(name, alias, win, winFn, winArg) {
      this.name = name;
      this.alias = alias != null ? alias : null;
      this.win = win != null ? win : null;
      this.winFn = winFn != null ? winFn : null;
      this.winArg = winArg != null ? winArg : null;
      null;
    }

    Table.prototype.toString = function() {
      if (this.win) {
        return "" + this.name + "." + this.win + ":" + this.winFn + "(" + this.winArg + ")";
      } else if (this.alias) {
        return "" + this.name + " AS " + this.alias;
      } else {
        return this.name.toString();
      }
    };

    return Table;

  })();

  exports.Group = Group = (function() {

    function Group(fields) {
      this.fields = fields;
      this.having = null;
    }

    Group.prototype.toString = function() {
      var ret;
      ret = ["GROUP BY " + (this.fields.join(', '))];
      if (this.having) ret.push(this.having.toString());
      return ret.join("\n");
    };

    return Group;

  })();

  exports.Where = Where = (function() {

    function Where(conditions) {
      this.conditions = conditions;
      null;
    }

    Where.prototype.toString = function() {
      return "WHERE " + this.conditions;
    };

    return Where;

  })();

  exports.Having = Having = (function() {

    function Having(conditions) {
      this.conditions = conditions;
      null;
    }

    Having.prototype.toString = function() {
      return "HAVING " + this.conditions;
    };

    return Having;

  })();

  exports.Op = Op = (function() {

    function Op(operation, left, right) {
      this.operation = operation;
      this.left = left;
      this.right = right;
      null;
    }

    Op.prototype.toString = function() {
      return "(" + this.left + " " + (this.operation.toUpperCase()) + " " + this.right + ")";
    };

    return Op;

  })();

  exports.UnaryOp = UnaryOp = (function() {

    function UnaryOp(operator, operand) {
      this.operator = operator;
      this.operand = operand;
      null;
    }

    UnaryOp.prototype.toString = function() {
      return "(" + (this.operator.toUpperCase()) + " " + this.operand + ")";
    };

    return UnaryOp;

  })();

  exports.Field = Field = (function() {

    function Field(field, name) {
      this.field = field;
      this.name = name != null ? name : null;
      null;
    }

    Field.prototype.toString = function() {
      if (this.name) {
        return "" + this.field + " AS " + this.name;
      } else {
        return this.field.toString();
      }
    };

    return Field;

  })();

  exports.Star = Star = (function() {

    function Star(table) {
      this.table = table != null ? table : null;
      null;
    }

    Star.prototype.toString = function() {
      if (this.table) {
        return "" + this.table + ".*";
      } else {
        return '*';
      }
    };

    Star.prototype.star = true;

    return Star;

  })();

  exports.CaseExpressionWhen = exports.CaseExpressionWhen = (function() {

    function CaseExpressionWhen(whenExp, thenExp) {
      this.whenExp = whenExp;
      this.thenExp = thenExp;
      null;
    }

    CaseExpressionWhen.prototype.toString = function() {
      return "WHEN " + this.whenExp + " THEN " + this.thenExp;
    };

    return CaseExpressionWhen;

  })();

  exports.CaseExpressionElse = exports.CaseExpressionElse = (function() {

    function CaseExpressionElse(elseExp) {
      this.elseExp = elseExp;
      null;
    }

    CaseExpressionElse.prototype.toString = function() {
      return "ELSE " + this.elseExp;
    };

    return CaseExpressionElse;

  })();

  exports.CaseExpression = exports.CaseExpression = (function() {

    function CaseExpression(caseExp, whenList, elseArg) {
      this.caseExp = caseExp;
      this.whenList = whenList;
      this.elseArg = elseArg != null ? elseArg : null;
      null;
    }

    CaseExpression.prototype.toString = function() {
      if (this.caseExp) {
        if (this.elseArg) {
          return "CASE " + this.caseExp + " " + (this.whenList.join(' ')) + " " + this.elseArg + " END";
        } else {
          return "CASE " + this.caseExp + " " + (this.whenList.join(' ')) + " END";
        }
      } else {
        if (this.elseArg) {
          return "CASE " + (this.whenList.join(' ')) + " " + this.elseArg + " END";
        } else {
          return "CASE " + (this.whenList.join(' ')) + " END";
        }
      }
    };

    return CaseExpression;

  })();

}).call(this);

};require['./parser'] = new function() {
  var exports = this;
  (function() {
  var buildParser;

  buildParser = function() {
    var parser;
    parser = require('./compiled_parser').parser;
    parser.lexer = {
      lex: function() {
        var tag, _ref;
        _ref = this.tokens[this.pos++] || [''], tag = _ref[0], this.yytext = _ref[1], this.yylineno = _ref[2];
        return tag;
      },
      setInput: function(tokens) {
        this.tokens = tokens;
        return this.pos = 0;
      },
      upcomingInput: function() {
        return "";
      }
    };
    parser.yy = require('./nodes');
    return parser;
  };

  exports.parser = buildParser();

  exports.parse = function(str) {
    return buildParser().parse(str);
  };

}).call(this);

};require['./sql_parser'] = new function() {
  var exports = this;
  (function() {

  exports.lexer = require('./lexer');

  exports.parser = require('./parser');

  exports.nodes = require('./nodes');

  exports.parse = function(sql) {
    return exports.parser.parse(exports.lexer.tokenize(sql));
  };

}).call(this);

};
    return require['./sql_parser']
  }();

  if(typeof define === 'function' && define.amd) {
    define(function() { return SQLParser });
  } else { root.SQLParser = SQLParser }
}(this));