oneweb.owjsPackage().DocumentReadyManager = {
	onewebQueue : {},

	applicationQueue : {},

	addOnewebAction : function(/* function */actionFn, /* int prority */priority, /* boolean */
			removeAfterRun) {
		this.addAction(this.onewebQueue, actionFn, priority, removeAfterRun);
	},

	addApplicationAction : function(/* function */actionFn, /* int prority */
			priority, /* boolean */removeAfterRun) {
		this.addAction(this.applicationQueue, actionFn, priority,
				removeAfterRun);
	},

	addAction : function(/* queue */queue, /* function */actionFn, /* int prority */
			priority, /* boolean */removeAfterRun) {
		if (actionFn == undefined || actionFn == null) {
			return;
		}

		var p;
		if (priority == undefined || priority == null) {
			p = 100;
		} else {
			p = Number(priority);
		}

		if (removeAfterRun == undefined || removeAfterRun == null) {
			removeAfterRun = true;
		}

		var array = queue[p];
		if (array == null || array == undefined) {
			array = new Array();
		}
		array.push({
			actionFn : actionFn,
			removeAfterRun : removeAfterRun
		});
		queue[p] = array;
	},

	runAllActions : function() {
		var priorities = new Array();
		for ( var priority in this.onewebQueue) {
			if (this.onewebQueue.hasOwnProperty(priority)) {
				priorities.push(Number(priority));
			}
		}
		priorities.sort(function(a, b) {
			return a - b
		});

		for ( var i = 0; i < priorities.length; i++) {
			var array = this.onewebQueue[priorities[i]];
			var newArray = [];
			for ( var j = 0; j < array.length; j++) {
				var o = array[j];
				o.actionFn();
				if (!o.removeAfterRun) {
					newArray.push(o);
				}
			}

			if (newArray.length > 0) {
				this.onewebQueue[priorities[i]] = newArray;
			} else {
				delete this.onewebQueue[priorities[i]]
			}
		}

		priorities = new Array();
		for ( var priority in this.applicationQueue) {
			if (this.applicationQueue.hasOwnProperty(priority)) {
				priorities.push(Number(priority));
			}
		}
		priorities.sort(function(a, b) {
			return a - b
		});

		for ( var i = 0; i < priorities.length; i++) {
			var array = this.applicationQueue[priorities[i]];
			var newArray = [];
			for ( var j = 0; j < array.length; j++) {
				var o = array[j];
				o.actionFn();
				if (!o.removeAfterRun) {
					newArray.push(o);
				}
			}

			if (newArray.length > 0) {
				this.applicationQueue[priorities[i]] = newArray;
			} else {
				delete this.applicationQueue[priorities[i]];
			}
		}
	}
};