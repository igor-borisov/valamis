curriculumManager.module('Entities', function(Entities, CurriculumManager, Backbone, Marionette, $, _) {

  Entities.SEARCH_TIMEOUT = 800;

  var CertificateService = new Backbone.Service({
    url: path.root,
    sync: {
      'create': {
        'path': path.api.certificates,
        'data': function (model, options) {
          return {
            courseId: Utils.getCourseId()
          }
        },
        'method': 'post'
      },
      'update': {
        'path':function (model) {
          return path.api.certificates + model.id
        },
        'data': function (model) {
          // send only necessary model fields
          var parameters = {
            courseId: Utils.getCourseId(),
            id: model.get('id'),
            title: model.get('title'),
            description: model.get('description'),
            scopeId: model.get('scopeId'),
            periodValue: model.get('validPeriod'),
            periodType: model.get('validPeriodType'),
            publishBadge: model.get('publishBadge'),
            shortDescription: model.get('shortDescription'),
            optionalGoals: model.get('optionalGoals')
          };

          return parameters
        },
        'method': 'put'
      },
      'read': {
        'path': function (model) {
          return path.api.certificates + model.id;
        },
        'data': {courseId: Utils.getCourseId()}
      },
      'delete': {
        'path': function (model) {
          return path.api.certificates + model.id
        },
        'data': {courseId: Utils.getCourseId()},
        'method': 'delete'
      }
    },
    targets: {
      'clone': {
        'path': function (model) {
          return path.api.certificates + model.id + '/do/clone'
        },
        'data': {courseId: Utils.getCourseId()},
        'method': 'post'
      },
      'publish': {
        'path': function (model) {
          return path.api.certificates + model.id + '/do/publish'
        },
        'data': {courseId: Utils.getCourseId()},
        'method': 'post'
      },
      'unpublish': {
        'path': function (model) {
          return path.api.certificates + model.id + '/do/unpublish'
        },
        'data': {courseId: Utils.getCourseId()},
        'method': 'post'
      }
    }
  });

  Entities.CertificateModel = Backbone.Model.extend({
    defaults: {
      scope: {id: ''},
      title: '',
      description: '',
      isPublished: false,
      isPermanent: true,
      periodValue: 0,
      periodType: 'UNLIMITED',
      isOpenBadgesIntegration: false
    },
    parse: function(response) {
      response.scopeId = (response.scope) ? response.scope.id : '';
      response.goalsCount =
          response.activityCount +
          response.courseCount +
          response.packageCount +
          response.statementCount +
          response.assignmentCount;

      return response;
    }
  }).extend(CertificateService);


  var CertificateCollectionService = new Backbone.Service({
    url: path.root,
    sync: {
      'read': {
        'path': path.api.certificates,
        'data': function (e, options) {
          var filter = options.filter;
          var sort = filter.sort;
          var sortBy = sort.split(':')[0];
          var asc = sort.split(':')[1];

          return {
            page: options.currentPage,
            count: options.itemsOnPage,
            filter: filter.searchtext,
            scopeId: filter.scopeId,
            sortBy: sortBy,
            sortAscDirection: asc,
            resultAs: 'short',
            courseId: Utils.getCourseId(),
            additionalData: 'itemsCount'
          }
        },
        method: 'get'
      }
    }
  });

  Entities.CertificateCollection = Backbone.Collection.extend({
    model: Entities.CertificateModel,
    parse: function (response) {
      this.trigger('certificateCollection:updated', { total: response.total, currentPage: response.currentPage });
      return response.records;
    }
  }).extend(CertificateCollectionService);

  // certificate goals

  Entities.LiferayActivityModel = Backbone.Model.extend({
    defaults: {
      activityId: '',
      selected: false
    },
    toggle: function(){
      var isSelected = this.get('selected');
      this.set('selected', !isSelected);
    }
  });

  Entities.LiferayActivityCollection = Backbone.Collection.extend({
    model: Entities.LiferayActivityModel
  });


  Entities.StatementPlugin = Backbone.Model.extend({
    label: function () {
      var activity = new TinCan.Activity(this.toJSON());
      return activity + ' (' + activity.id + ')';
    }
  });

  Entities.StatementPluginCollection = Backbone.Collection.extend({
    url: path.root + path.api.activities + 'search' + '?courseId=' + Utils.getCourseId(),
    model: Entities.StatementPlugin
  });

  Entities.StatementModel = Backbone.Model.extend({
    defaults: {
      language: '',
      verb: 'http://adlnet.gov/expapi/verbs/answered',
      verbName: 'answered',
      verbs: [
        { id: 'http://adlnet.gov/expapi/verbs/answered', title: 'answered'},
        { id: 'http://adlnet.gov/expapi/verbs/asked', title: 'asked' },
        { id: 'http://adlnet.gov/expapi/verbs/attempted', title: 'attempted' },
        { id: 'http://adlnet.gov/expapi/verbs/attended', title: 'attended' },
        { id: 'http://adlnet.gov/expapi/verbs/commented', title: 'commented'},
        { id: 'http://adlnet.gov/expapi/verbs/completed', title: 'completed'},
        { id: 'http://adlnet.gov/expapi/verbs/exited', title: 'exited' },
        { id: 'http://adlnet.gov/expapi/verbs/experienced', title: 'experienced' },
        { id: 'http://adlnet.gov/expapi/verbs/failed', title: 'failed'},
        { id: 'http://adlnet.gov/expapi/verbs/imported', title: 'imported'},
        { id: 'http://adlnet.gov/expapi/verbs/initialized', title: 'initialized'},
        { id: 'http://adlnet.gov/expapi/verbs/launched', title: 'launched'},
        { id: 'http://adlnet.gov/expapi/verbs/mastered', title: 'mastered'},
        { id: 'http://adlnet.gov/expapi/verbs/passed', title: 'passed'},
        { id: 'http://adlnet.gov/expapi/verbs/preferred', title: 'preferred'},
        { id: 'http://adlnet.gov/expapi/verbs/progressed', title: 'progressed'},
        { id: 'http://adlnet.gov/expapi/verbs/registered', title: 'registered'},
        { id: 'http://adlnet.gov/expapi/verbs/responded', title: 'responded'},
        { id: 'http://adlnet.gov/expapi/verbs/resumed', title: 'resumed'},
        { id: 'http://adlnet.gov/expapi/verbs/scored', title: 'scored'},
        { id: 'http://adlnet.gov/expapi/verbs/shared', title: 'shared'},
        { id: 'http://adlnet.gov/expapi/verbs/suspended', title: 'suspended'},
        { id: 'http://adlnet.gov/expapi/verbs/terminated', title: 'terminated'},
        { id: 'http://adlnet.gov/expapi/verbs/voided', title: 'voided'},
        { id: 'http://adlnet.gov/expapi/verbs/interacted', title: 'interacted'}
      ],
      obj: '',
      objName: '',
      selected: false
    },
    toggle: function(){
      var isSelected = this.get('selected');
      this.set('selected', !isSelected);
    },
    parse: function(model) {
      model.verbName = Utils.getLangDictionaryTincanValue(model.verbName);
      model.objName = Utils.getLangDictionaryTincanValue(model.objName);
      return model;
    }
  });

  var StatementCollectionService = new Backbone.Service({
    url: path.root,
    sync: {
      'read': {
        'path': path.api.statements,
        'data': function (e, options) {
          var order = options.filter.sort;
          var sortBy = order.split(':')[0];
          var asc = order.split(':')[1];

          return {
            courseId: Utils.getCourseId(),
            page: options.currentPage,
            count: options.itemsOnPage,
            filter: options.filter.searchtext,
            sortAscDirection: asc,
            sortBy: sortBy
          }
        },
        'method': 'get'
      }
    }
  });

  Entities.StatementsCollection = Backbone.Collection.extend({
    model: Entities.StatementModel,
    parse: function (response) {
      this.trigger('statementsCollection:updated', {
        total: response.total,
        currentPage: response.currentPage
      });
      return response.records;
    }
  }).extend(StatementCollectionService);

  Entities.GOAL_TYPE = valamisApp.Entities.GOAL_TYPE;

  function getGoalUrlPostfix(goalType) {
    var severalGoalsPostfix = {};
    severalGoalsPostfix[Entities.GOAL_TYPE.COURSE] = '/courses';
    severalGoalsPostfix[Entities.GOAL_TYPE.STATEMENT] = '/statements';
    severalGoalsPostfix[Entities.GOAL_TYPE.ACTIVITY] = '/activities';
    severalGoalsPostfix[Entities.GOAL_TYPE.PACKAGE] = '/packages';
    severalGoalsPostfix[Entities.GOAL_TYPE.ASSIGNMENT] = '/assignments';

    var p = severalGoalsPostfix[goalType];

    if (p === undefined)
      console.error('wrong goal type');

    return p;
  }

  var GoalModelService = new Backbone.Service({
    url: path.root,
    sync: {
      'update': {
        'path': function(model){
          return path.api.certificates + model.get('certificateId')  + '/goal/' + model.get('id');
        },
        'data': function (model) {
          var parameters = {courseId: Utils.getCourseId()};
          _.extend(parameters, model.toJSON());
          return parameters;
        },
        'method': 'put'
      },
      'delete': {
        'path': function(model){
          return path.api.certificates + model.get('certificateId') + '/goal/'+ model.get('id');
        },
        'data': function(model) {
          var parameters = {courseId: Utils.getCourseId()};
          _.extend(parameters, model.toJSON());
          return parameters;
        },
        'method': 'delete'
      }
    }
  });

  Entities.GoalModel = Backbone.Model.extend({
    idAttribute: 'uniqueId',
    defaults: {
      isActivity: false,
      noDate: false,
      selected: false,
      hidden: false
    },
    isSelected: function() {
      return this.get('selected') && !this.get('hidden');
    }
  }).extend(GoalModelService);

  var GoalGroupModelService = new Backbone.Service({
    url: path.root,
    sync: {
      'update': {
        path: function (model) {
          return path.api.certificates + model.get('certificateId') + '/group/' + model.get('id');
        },
        'data': function (model) {
          var params = {
            courseId:  Utils.getCourseId(),
            periodValue: model.get('periodValue'),
            periodType: model.get('periodType'),
            goalCount: model.get('goalCount'),
            arrangementIndex: model.get('arrangementIndex')
          };
          return params;
        },
        method: 'put'
      },
      'delete': {
        path: function (model) {
          return path.api.certificates + model.get('certificateId') + '/group/' + model.get('id');
        },
        'data': function (model, options) {
          var params = {
            courseId:  Utils.getCourseId(),
            deletedContent: options.deletedContent
          };
          return params;
        },
        method: 'delete'
      }
    }
  });

  Entities.GoalGroupModel = Backbone.Model.extend({
    idAttribute: 'uniqueId',
    defaults: {
      goalCount: 1,
      selected: false,
      hidden: false
    },
    isSelected: function() {
      return this.get('selected') && !this.get('hidden');
    }
  }).extend(GoalGroupModelService);

  var GoalCollectionService = new Backbone.Service({
    url: path.root,
    sync: {
      'read': {
        'path': function (collection) {
          return path.api.certificates + collection.certificateId + '/goals'
        },
        'data': {
          courseId: Utils.getCourseId()
        },
        'method': 'get'
      }
    },
    targets: {
      saveToCertificate: {
        path: function (collection, options) {
          return path.api.certificates + collection.certificateId + getGoalUrlPostfix(options.type);
        },
        'data' : function(model, options){
          var params = {
            courseId:  Utils.getCourseId()
          };
          params[options.key] = options.value;

          return params;
        },
        method: 'post'
      },
      updateGoalIndexes: {
        path: function (collection) {
          return path.api.certificates + collection.certificateId + '/goals/indexes';
        },
        'data': function (model, options) {
          var params = {
            courseId:  Utils.getCourseId(),
            goalIndexes: JSON.stringify(options.goalIndexes)

          };
          return params;
        },
        method: 'post'
      },
      'groupGoals': {
        path: function (collection) {
          return path.api.certificates + collection.certificateId + '/group';
        },
        'data': function (model, options) {
          var params = {
            courseId:  Utils.getCourseId(),
            goalCount: 1,
            goalIds: options.goalIds
          };
          return params;
        },
        method: 'post'
      },
      'updateGroupGoals': {
        path: function (collection, options) {
          return path.api.certificates + collection.certificateId + '/group/'
            + options.groupId + '/goals';
        },
        'data': function (model, options) {
          var params = {
            courseId:  Utils.getCourseId(),
            goalIds: options.goalIds
          };
          return params;
        },
        method: 'put'
      }
    }
  });

  Entities.GoalsCollection = valamisApp.Entities.BaseGoalsCollection.extend({
    model: function (attrs, options) {
      if (attrs.isGroup)
        return new Entities.GoalGroupModel(attrs, options);
      else
        return new Entities.GoalModel(attrs, options);
    },
    initialize: function(models, options) {
      this.certificateId = options.certificateId;
    },
    parse: function(response) {
      this.trigger('goalsCollection:updated', response.goals.length);
      var singleGoals = _.filter(response.goals, function(goal){
        return goal.goalData.groupId == undefined
      });

      var that = this;
      _.each(response.groups, function(item) {
        var filteredGoals = _.filter(response.goals, function (goal) {
          return goal.goalData.groupId == item.id
        });
        item.collection = new Entities.GoalsCollection(
          that.toGroupResponse(filteredGoals),
          { certificateId: that.certificateId }
        );
        item.isGroup = true;
        item.uniqueId = 'group_' + item.id;
      });

      return [].concat(this.toGroupResponse(singleGoals), response.groups);
    },
    getGoalById: function(id) {
      var foundModel;
      this.each(function(model) {
        if (model.get('isGroup')) {
          foundModel = foundModel || model.get('collection').getGoalById(id);
        } else
          if (model.get('id') == id)
            foundModel = model;
      });

      return foundModel;
    },
    filterCollection: function(filter) {
      this.forEach(function(model) {
        var patternMatch;
        var coll = model.get('collection');
        if (coll) {
          coll.filterCollection(filter);
          patternMatch = (coll.filter(function(model) { return !model.get('hidden') }).length > 0);
        }
        else
          patternMatch = _.contains(model.get('title').toLowerCase(), filter.toLowerCase());

        model.set('hidden', !patternMatch);
      });
    },
    toggleSelectAll: function(selected) {
      this.forEach(function(model) {
        model.set('selected', selected);
        if (model.get('isGroup'))
          model.get('collection').toggleSelectAll(selected);
      })
    }
  }).extend(GoalCollectionService);

  // certificate members

  var MemberModelService = new Backbone.Service({
    url: path.root,
    sync: {
      'delete': {
        'path': function (model, options) {
          return path.api.certificates + options.certificateId + '/users'
        },
        'data': function (model) {
          return {
            courseId: Utils.getCourseId(),
            userIds: model.id
          }
        },
        'method': 'delete'
      }
    },
    targets: {
      'getGoalsStatuses': {
        'path': function (model, options) {
          return path.api.users + model.get('id') + '/certificates/' + options.certificateId + '/goals';
        },
        method: 'get'
      }
    }
  });

  Entities.MemberModel = Backbone.Model.extend({
    defaults: {
      selected: false
    }
  }).extend(MemberModelService);

  var MembersCollectionService = new Backbone.Service({
    url: path.root,
    sync: {
      'read': {
        'path': function (model, options) {
          return path.api.certificates + options.filter.certificateId + '/member'
        },
        'data': function (collection, options) {

          var order = options.filter.sort;
          var sortBy = order.split(':')[0];
          var asc = order.split(':')[1];

          var params = {
            courseId: Utils.getCourseId(),
            certificateId: options.filter.certificateId,
            memberType: options.filter.memberType,
            sortBy: sortBy,
            sortAscDirection: asc,
            filter: options.filter.searchtext,
            page: options.currentPage,
            count: options.itemsOnPage
          };

          params.action = (options.filter.available) ? 'AVAILABLE_MEMBERS' : 'MEMBERS';

          var organizationId = options.filter.orgId;
          if (organizationId)
            _.extend(params, {orgId: organizationId});

          return params;
        },
        'method': 'get'
      }
    },
    targets: {
      'deleteFromCertificate': {
        'path': function (model, options) {
          return path.api.certificates + options.certificateId + '/members'
        },
        'data': function (model, options) {
          return {
            courseId: Utils.getCourseId(),
            memberIds: options.memberIds,
            memberType: options.memberType
          };
        },
        method: 'delete'
      },
      saveToCertificate: {
        path: function (model, options) {
          return path.api.certificates + options.certificateId + '/member';
        },
        'data' : function(model, options){
          var params = {
            courseId:  Utils.getCourseId(),
            memberIds : options.memberIds,
            memberType: options.memberType
          };

          return params;
        },
        method: 'post'
      }
    }
  });

  Entities.MembersCollection = Backbone.Collection.extend({
    model: Entities.MemberModel,
    parse: function (response) {
      this.trigger('userCollection:updated', { total: response.total, currentPage: response.currentPage });
      return response.records;
    },
    getSelected: function() {
      return this.filter(function(item) { return item.get('selected') });
    }
  }).extend(MembersCollectionService);

});