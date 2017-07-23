(function () {
    var BladeComboTree = function () {
        this.id = "";
        this.search = "";
        this.type = "combotree";
        this.source = "";
        this.where = "";
        this.val = "";
        this.treeId = "id";
        this.intercept = "";
        this.check = "";
        this.width = "180px";
        this.height = "250px";
        this.fix = true;
        this.ext = "";
    }

    BladeComboTree.prototype = {
        setId : function (id) {
            this.id = id;
        },
        setType : function (type) {
            this.type = type;
        },
        setSource : function (source) {
            this.source = source;
        },
        setWhere : function (where) {
            this.where = where;
        },
        setVal : function (val) {
            this.val = val;
        },
        setTreeId : function (treeId) {
            this.treeId = treeId;
        },
        setIntercept : function (intercept) {
            this.intercept = intercept;
        },
        setCheck : function (check) {
            this.check = check;
        },
        setWidth : function (width) {
            this.width = width;
        },
        setHeight : function (height) {
            this.height = height;
        },
        setExt : function (ext) {
            this.ext = ext;
        },
        beforeClick : function (treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj(treeId);
            zTree.checkNode(treeNode, !treeNode.checked, null, true);
            return false;
        },
        onCheck : function (e, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj(treeId),
                nodes = zTree.getCheckedNodes(true),
                ids = "",
                v = "";
            for (var i = 0, l = nodes.length; i < l; i++) {
                ids += nodes[i].id + ",";
                v += nodes[i].name + ",";
            }
            if (v.length > 0 ) v = v.substring(0, v.length - 1);
            if (ids.length > 0 ) ids = ids.substring(0, ids.length - 1);
            var _id = treeId.replace("tree_", "").replace("_ipt", "");
            var treeInput = $("#" + _id + "_ipt");
            var treeHidden = $("#" + _id + "");
            treeInput.val(v);
            treeHidden.val(ids);
            $("#form_token").val(1);

            //用于点击后的回调
            if (typeof comboTreeCallBack == 'function') {
                comboTreeCallBack(_id, ids);
            }

        },
        showMenu : function () {
            var _id = this.id;
            var _me = this;
            var $ipt = $("#" + _id + "_ipt");
            $("#tree_" + _id + "_ipt").css({"width": $ipt.css("width")});
            var top = $ipt.outerHeight();
            if (this.fix) {
                top = top + $ipt.offset().top - 1;
            }
            $("#treeContent_" + _id + "_ipt").css({top:top + "px"}).slideDown("fast");
            $("body").bind("mousedown", function (event) {
                if (!(event.target.id == "menuBtn" || event.target.id == ""+_id+"_ipt" || event.target.id == "treeContent_"+_id+"_ipt" || $(event.target).parents("#treeContent_"+_id+"_ipt").length>0)) {
                    _me.hideMenu();
                }
            });
        },
        hideMenu : function () {
            var _id = this.id;
            var _me = this;
            $("#treeContent_" + _id + "_ipt").fadeOut("fast");
            $("body").unbind("mousedown", function (event) {
                if (!(event.target.id == "menuBtn" || event.target.id == ""+id+"_ipt" || event.target.id == "treeContent_"+id+"_ipt" || $(event.target).parents("#treeContent_"+id+"_ipt").length>0)) {
                    _me.showMenu();
                }
            });
        },
        searchMenu : function () {
            var setting;

            if (this.check == "radio") {
                setting = {
                    check: {
                        enable: true,
                        chkStyle: "radio",
                        radioType : "all"
                    },
                    view: {
                        dblClickExpand: false,
                        fontCss: getFont,
                        nameIsHTML: true
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    },
                    callback: {
                        beforeClick: this.beforeClick,
                        onCheck: this.onCheck
                    }
                }
            } else {
                setting = {
                    check: {
                        enable: true,
                        chkboxType :{ "Y" : "", "N" : "" }
                    },
                    view: {
                        dblClickExpand: false,
                        fontCss: getFont,
                        nameIsHTML: true
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    },
                    callback: {
                        beforeClick: this.beforeClick,
                        onCheck: this.onCheck
                    }
                }
            }

            function getFont(treeId, node) {
                return node.font ? node.font : {};
            }

            var _id = this.id;
            var _node_data;
            var treeList = new Ajax(BladeApp.getCtx() + "/combotree/getTreeList", function(data){
                if (data.code === 0) {
                    _node_data = data.data;
                    $.fn.zTree.init($("#tree_" + _id + "_ipt"), setting, _node_data);
                    match();
                } else {
                    layer.alert(data.message, {icon: 2,title:"发生错误"});
                }
            });
            treeList.set("search", $("#" + _id + "_ipt").val());
            treeList.set("type", this.type);
            treeList.set("source", this.source);
            treeList.set("where", this.where);
            treeList.set("intercept", this.intercept);
            treeList.set("ext", this.ext);
            treeList.set("val", 0);
            treeList.set("treeId", this.treeId);
            treeList.start();


            function match() {
                var search = $("#" + _id + "_ipt").val();
                if (BladeTool.isEmpty(search)) {
                    //layer.msg("请输入关键字", {shift: 6,time:2000});
                    return false;
                }
                var zTree = $.fn.zTree.getZTreeObj("tree_" + _id + "_ipt");
                for (var i = 0; i < _node_data.length; i++) {
                    var name = _node_data[i].name;
                    if (name.contains(search)) {
                        var node = zTree.getNodeByParam("name", name);
                        node.font = {'font-weight':'bold', 'color':'#009688'};
                        zTree.updateNode(node);
                        zTree.expandNode(node, true, false);//指定选中ID节点展开
                        expandParentNode(node, zTree);
                    }

                }
            }

            //递归打开ztree节点
            function expandParentNode(_node, zTree) {
                var _pNode = _node.getParentNode();//获得父节点
                if (null != _pNode) {
                    if (!_pNode.open) {
                        zTree.expandNode(_pNode, true, false);//指定选中ID节点展开
                    }
                    expandParentNode(_pNode, zTree);
                }
            }

        },
        initComboTree : function () {
            var setting;

            if (this.check == "radio") {
                setting = {
                    check: {
                        enable: true,
                        chkStyle: "radio",
                        radioType : "all"
                    },
                    view: {
                        dblClickExpand: false
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    },
                    callback: {
                        beforeClick: this.beforeClick,
                        onCheck: this.onCheck
                    }
                }
            } else {
                setting = {
                    check: {
                        enable: true,
                        chkboxType :{ "Y" : "", "N" : "" }
                    },
                    view: {
                        dblClickExpand: false
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    },
                    callback: {
                        beforeClick: this.beforeClick,
                        onCheck: this.onCheck
                    }
                }
            }

            var _id = this.id;
            var _val = $("#" + this.id).val();

            var treeList = new Ajax(BladeApp.getCtx() + "/combotree/getTreeList", function(data){
                if (data.code === 0) {
                    $.fn.zTree.init($("#tree_" + _id + "_ipt"), setting, data.data);
                } else {
                    layer.alert(data.message, {icon: 2,title:"发生错误"});
                }
            });
            treeList.set("search", "");
            treeList.set("val", _val);
            treeList.set("type", this.type);
            treeList.set("source", this.source);
            treeList.set("where", this.where);
            treeList.set("intercept", this.intercept);
            treeList.set("ext", this.ext);
            treeList.set("treeId", this.treeId);
            treeList.start();


            var treeName = new Ajax(BladeApp.getCtx() + "/combotree/getTreeListName", function(data){
                if (data.code === 0) {
                    $("#" + _id + "_ipt").val(data.data);
                } else {
                    layer.alert(data.message, {icon: 2,title:"发生错误"});
                }
            });
            treeName.set("type", this.type);
            treeName.set("source", this.source);
            treeName.set("where", this.where);
            treeName.set("val", _val);
            treeName.set("treeId", this.treeId);
            treeName.start();

        },
        reset: function () {
            var setting;

            if (this.check == "radio") {
                setting = {
                    check: {
                        enable: true,
                        chkStyle: "radio",
                        radioType : "all"
                    },
                    view: {
                        dblClickExpand: false
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    },
                    callback: {
                        beforeClick: this.beforeClick,
                        onCheck: this.onCheck
                    }
                }
            } else {
                setting = {
                    check: {
                        enable: true,
                        chkboxType :{ "Y" : "", "N" : "" }
                    },
                    view: {
                        dblClickExpand: false
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    },
                    callback: {
                        beforeClick: this.beforeClick,
                        onCheck: this.onCheck
                    }
                }
            }

            var _id = this.id;
            var _val = $("#" + this.id).val();

            var treeList = new Ajax(BladeApp.getCtx() + "/combotree/getTreeList", function(data){
                if (data.code === 0) {
                    $.fn.zTree.init($("#tree_" + _id + "_ipt"), setting, data.data);
                } else {
                    layer.alert(data.message, {icon: 2,title:"发生错误"});
                }
            });
            treeList.set("search", "");
            treeList.set("val", _val);
            treeList.set("type", this.type);
            treeList.set("source", this.source);
            treeList.set("where", this.where);
            treeList.set("intercept", this.intercept);
            treeList.set("ext", this.ext);
            treeList.set("treeId", this.treeId);
            treeList.start();
        },
        init : function () {

            var _me = this;

            var $ipt = $("#" + this.id);

            var _val = $ipt.val();

            $ipt.attr("id", this.id + "_ipt");

            var html = [];
            html.push('<input id="' + this.id + '" name="' + this.id + '" value="'+_val+'" class="form-control" data-type="combotree"  type="hidden" />');
            html.push('<div id="treeContent_' + this.id + '_ipt" class="menuContent" style="display:none; position: absolute;z-index:2333333333;">');
            html.push('<ul id="tree_' + this.id + '_ipt" class="ztree" style="border-radius: 0 !important;-webkit-box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);background: #fafafa;border: 1px solid rgba(0, 0, 0, 0.15);border: 1px solid #cccccc;overflow-y: scroll;overflow-x: auto;margin-top:0; width:'+this.width+'; max-height: '+this.height+';"></ul>');
            html.push('</div>');
            $ipt.after(html.join(""));

            if (BladeTool.isEmpty(this.val)) {
                this.val = _val;
            }

            this.initComboTree();

            $ipt.bind("click", function(){
                _me.showMenu();
            });
            $ipt.bind("keydown", function(e){
                if (e.keyCode == 13) {
                    _me.searchMenu();
                }
            });
        }
    }

    window.BladeComboTree = BladeComboTree;

    document.write('<link rel="stylesheet" href="' + BladeApp.getCtx() + '/static/zTree/css/zTreeStyle/zTreeStyle.css" />');
    document.write('<script src="' + BladeApp.getCtx() + '/static/zTree/js/jquery.ztree.core.js" type="text/javascript" ></script>');
    document.write('<script src="' + BladeApp.getCtx() + '/static/zTree/js/jquery.ztree.excheck.js" type="text/javascript"></script>');

}());