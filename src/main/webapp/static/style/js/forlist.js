/**
 * 表格默认配置
 */
var item_selected = [];
jQuery.extend(jQuery.jgrid.defaults, {
    datatype: "json",
    autowidth: true,
    shrinkToFit: false,
    rownumWidth: 50,
    altRows: true,
    multiselect: true,
    multiboxonly: true,
    jsonReader: { id: "id" }, //将后台返回的id设置为表的主键
    viewrecords: true,
    rowNum: 20,
    rowList: [10, 20, 30, 50, 100],
    rownumbers: true,
    loadComplete: function () {
        var table = this;
        setTimeout(function () {
            updatePagerIcons(table);
            //自适应表格高度 -- 表格初始化后调用
            autoResize(grid_selector, 225);
            $jqGrid = $(grid_selector);
        }, 0);

        //grid加载完毕，从数组中选中对应的记录
        if(item_selected.length>0){
            for (var i = 0; i < item_selected.length; i++) {
                $jqGrid.jqGrid('setSelection',item_selected[i]);
            }
        }
    }
    ,onSelectAll:function(aRowids,status) {
        //全选事件触发的事件
        if(status){
            item_selected = _.union(item_selected, aRowids);
        }else{
            item_selected = _.difference(item_selected, aRowids);
        }
    },
    beforeSelectRow:function(rowid, e) {

        //CheckBox区域的td点击后不触发选中事件

        var flag = true
        if (e.target.firstChild != undefined || e.target.nodeName == "IMG") {
            /*
            //选择非CheckBox区域，禁止默认点击事件，调用该行对应的CheckBox的点击事件
            if(e.target.firstChild.className == undefined) {
                $("#jqg_grid-table_" + rowid).click()
                flag = false
            }
            //选择CheckBox区域的td区域，禁止默认点击事件，调用该行对应的CheckBox的点击事件
            if(e.target.firstChild.className == "cbox") {
                $("#" + e.target.firstChild.id).click()
                flag = false
            }
            */
            $("#jqg_grid-table_" + rowid).click()
            flag = false
        }
        if (e.target.type == "checkbox"){
            //CheckBox的点击事件
            flag = true
        }

        if (flag) {
            //点击则从数组增加，否则从数组移除
            var index = _.indexOf(item_selected, rowid);
            if(index==-1){
                item_selected.push(rowid);
            }
            else{
                item_selected = _.pull(item_selected, rowid);
            }
        }

        return flag
    }
});



//replace icons with FontAwesome icons like above
function updatePagerIcons(table) {
    var replacement =
        {
            'ui-icon-seek-first': 'ace-icon fa fa-angle-double-left bigger-140',
            'ui-icon-seek-prev': 'ace-icon fa fa-angle-left bigger-140',
            'ui-icon-seek-next': 'ace-icon fa fa-angle-right bigger-140',
            'ui-icon-seek-end': 'ace-icon fa fa-angle-double-right bigger-140'
        };
    $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function () {
        var icon = $(this);
        var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

        if ($class in replacement) icon.attr('class', 'ui-icon ' + replacement[$class]);
    });
};


/**
 * jqgrid自适应屏幕
 * @author taoyunjie
 * @param id,fixHeight
 * @returns
 */
function autoResize(idObj, fixHeight) {
    //初始化时
    $(idObj).setGridHeight($(window).height() - fixHeight);
    //窗口变动时
    $(window).resize(function () {
        $(idObj).setGridHeight($(window).height() - fixHeight);
        $(idObj).jqGrid('setGridWidth', $(".tb-grid").width());
    });
    //菜单收缩时
    var parent_column = $(idObj).closest('[class*="col-"]');
    $(document).on('settings.ace.jqGrid', function (ev, event_name, collapsed) {
        if (event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed') {
            //setTimeout is for webkit only to give time for DOM changes and then redraw!!!
            setTimeout(function () {
                $(idObj).jqGrid('setGridWidth', parent_column.width());
            }, 0);
        }
    });
};


//获取表格单条记录xl（多条时 选取最后选中的）
function getGridXl() {
    return item_selected[0];
    //return $jqGrid.jqGrid('getGridParam', 'selrow');
}


//获取多行记录的xl值
function getGridXls() {
    return item_selected;
    //return $jqGrid.jqGrid("getGridParam", "selarrrow");
}

function getRowData() {
    return $jqGrid.jqGrid("getRowData", getGridXl()); //根据上面的id获得本行的所有数据
}

//刷新grid
function reloadGrid() {
    exwhere = "";
    var filter = "";
    if (typeof (_filter) != "undefined") {
        filter = _filter;
    }
    item_selected = [];
    $jqGrid.jqGrid('setGridParam', { postData: { where: filter }, page: 1 }).trigger("reloadGrid");
}

//查询grid
function searchGrid() {
    $("#gotoSearch").click();
}
