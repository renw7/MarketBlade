(function () {
    var CacheGrid = function () {
        this.title = [];
        this.field = [];
        this.pk = "id";
        this.table = "";
        this.data = new JMap();
        this.keys = new Array();
        this.checkBox = true;
        this.count = 0;
        this.noWidth = 3.5;
        this.pageDetail = "";
    };

    CacheGrid.prototype = {
        setPk: function (pk) {
            this.pk = pk;
        },
        setNoWidth: function (width) {
            this.noWidth = width;
        },
        setCheckBox: function (checkBox) {
            this.checkBox = checkBox;
        },
        setTable: function (table) {
            this.table = table;
        },
        setTitle: function (title) {
            this.title = title;
        },
        setField: function (field) {
            this.field = field;
        },
        setData: function (data) {
            this.count = 0;
            this.data = new JMap();
            var keys = data.keys().sortNumber();
            for (var k = 0; k < keys.length; k++) {
                var _key = keys[k];
                var _map = data.get(_key);
                this.count++;
                this.data.put(this.count, _map);
            }
        },
        setDataByKey: function (data) {
            this.count = 0;
            this.data = new JMap();
            var keys = data.keys().sortNumber();
            for (var k = 0; k < keys.length; k++) {
                var _key = keys[k];
                var _map = data.get(_key);
                this.count = _key;
                this.data.put(_key, _map);
            }
        },
        setPageDetail:function (pageDetail) {
            this.pageDetail = pageDetail;
        },
        getData: function () {
            return this.data;
        },
        getSelectKey: function () {
            return this.keys[0];
        },
        getSelectKeys: function () {
            return this.keys.unique();
        },
        getSelectRow: function () {
            var selectKey = this.keys[0];
            return this.data.get(selectKey);
        },
        getSelectRows: function () {
            var selectRows = new JMap();
            var selectKeys = this.keys.unique();
            for (var i = 0; i < selectKeys.length; i++) {
                var key = selectKeys[i];
                selectRows.put(key, this.data.get(key));
            }
            return selectRows;
        },
        zero: function () {
            return (this.getSelectKeys().length == 0);
        },
        one: function () {
            return (this.getSelectKeys().length == 1);
        },
        multi: function () {
            return (this.getSelectKeys().length > 1);
        },
        rowId: function () {
            return this.count;
        },
        select: function (key) {
            debugger;
            $("#" + this.table + "_body_tr_" + key).click();
        },
        add: function (_map) {
            var me = this;
            //如果没有数据则清空
            if (this.data.size() == 0) {
                $("#" + this.table + "_body").html("");
            }
            this.count++;
            var _key = this.count;
            this.data.put(_key, _map);
            clickCheckbox(me, _key, _map);
        },
        update: function (_key, _map) {
            this.data.remove(_key);
            this.data.put(_key, _map);
            this.keys.remove(_key);
            var field = [];
            if (this.checkBox) {
                field.push("<td style='vertical-align:middle;width:2%;text-align:center;'>");
                field.push("<div class='cover-checkbox' style='position:relative;width: 100%; height: 100%;top: 0px; left: 0px;'>");
                field.push("	<input id='" + this.table + "_grid_checkbox_" + _key + "' data-key='" + _key + "' type='checkbox' name='" + this.table + "_grid_checkbox' />");
                field.push("    <div style='position:absolute;z-index: 2;width: 100%; height: 100%;top: 0px; left: 0px;opacity: 1;'></div>");
                field.push("</div>");
                field.push("</td>");
            }
            field.push("<td style='vertical-align:middle;width:" + this.noWidth + "%;text-align:right;'>");
            field.push(_key);
            field.push("</td>");
            for (var f = 0; f < this.field.length; f++) {
                var _value = _map.get(this.field[f].name);
                var _style = "";
                if (this.field[f].hidden) {
                    _style += "display: none;";
                }
                if (typeof this.field[f].align === "string") {
                    _style += "text-align: " + this.field[f].align + ";";
                }
                field.push("<td id='" + this.table + "_body_td_" + this.field[f].name + "_" + _key + "' style='" + _style + "vertical-align:middle;width:" + this.field[f].width + "%;' >");
                if (typeof this.field[f].format === 'function') {
                    field.push(this.field[f].format(_map, _key));
                } else {
                    field.push(_value);
                }
                field.push("</td>");
            }
            $("#" + this.table + "_body_tr_" + _key).html(field.join(""));
            $("#" + this.table + "_body_tr_" + _key).removeAttr("style");
        },
        remove: function (_key) {
            this.data.remove(_key);
            this.keys.remove(_key);
            $("#" + this.table + "_body_tr_" + _key).remove();
            if (this.data.size() == 0) {
                $("#" + this.table + "_body").html("<tr><td colspan='" + (this.title.length + 2) + "' style='text-align:center;'>暂无数据</td></tr>");
            }
        },
        removeMulti: function (_keys) {
            for (var i = 0; i < _keys.length; i++) {
                var key = _keys[i];
                this.remove(key);
            }
        },
        init: function () {
            this.keys = new Array();
            var me = this;
            var $table = $("#" + this.table);
            $table.html("");
            var thead = [];
            thead.push("<thead>");
            thead.push("<tr>");
            if (this.checkBox) {
                thead.push("<th style='vertical-align:middle;text-align:center;'><input id='" + this.table + "_chb' type='checkbox' /></th>");
            }
            thead.push("<th>序号</th>");
            for (var i = 0; i < this.title.length; i++) {
                var _display = "";
                if (this.field[i].hidden) {
                    _display = "style='display:none;'";
                }
                thead.push("<th" + _display + ">");
                thead.push(this.title[i]);
                thead.push("</th>");
            }
            thead.push("</tr>");
            thead.push("</thead>");
            $table.append(thead.join(""));
            var $tbody = $("<tbody id='" + this.table + "_body'></table>").appendTo($table);

            //加载分页信息
            if (BladeTool.isNotEmpty(this.pageDetail) && this.pageDetail.total > 0) {
                var html = [];
                html.push('<span id="sp_grid"></span>');
                html.push('<span>每页显示:</span>');
                html.push('<select id="sel_page" style="border:1px solid #dddddd;" onchange="changePage()">');
                html.push('     <option value="8">8</option>');
                html.push('     <option value="20">20</option>');
                html.push('     <option value="50">50</option>');
                html.push('     <option value="100000000">全部</option>');
                html.push('</select>');
                html.push('<span>条</span>');
                $("#grid_pages").html(html.join(""));
                $("#sel_page").val(this.pageDetail.pageSize);
                var rowEnd = (this.pageDetail.rowEnd == 100000000) ? "∞" : this.pageDetail.rowEnd;
                $("#sp_grid").html("显示第" + this.pageDetail.rowBegin + "到第" + rowEnd + "条记录,共" + this.pageDetail.records + "条记录,");
            }

            if (this.data.size() === 0) {
                $tbody.html("<tr><td colspan='" + (this.title.length + 2) + "' style='text-align:center;'>暂无数据</td></tr>");
                $("#" + this.table + "_chb").bind("click", function () {
                    var checked = $(this).is(":checked");
                    $("input[name='" + me.table + "_grid_checkbox']").each(function () {
                        var data_key = $(this).attr("data-key");
                        $(this).prop("checked", checked);
                        if (checked) {
                            $(this).closest("tr").css("background-color", "#f5f5f5");
                            me.keys.push(data_key);
                        } else {
                            $(this).closest("tr").removeAttr("style");
                            me.keys.remove(data_key);
                        }
                    });
                    me.keys = me.keys.unique();
                });
                return false;
            }
            //传入的data参数为一个ListMap
            var keys = this.data.keys().sortNumber();
            for (var k = 0; k < keys.length; k++) {
                //取到每一行的map对象
                var _key = keys[k];
                var _map = this.data.get(_key);
                clickCheckbox(me, _key, _map);
            }

            $("#" + this.table + "_chb").bind("click", function () {
                var checked = $(this).is(":checked");
                $("input[name='" + me.table + "_grid_checkbox']").each(function () {
                    var data_key = $(this).attr("data-key");
                    $(this).prop("checked", checked);
                    if (checked) {
                        $(this).closest("tr").css("background-color", "#f5f5f5");
                        me.keys.push(data_key);
                    } else {
                        $(this).closest("tr").removeAttr("style");
                        me.keys.remove(data_key);
                    }
                });
                me.keys = me.keys.unique();
            });
        }
    };
    window.CacheGrid = CacheGrid;

    function clickCheckbox(me, _key, _map) {
        var field = [];
        field.push("<tr id='" + me.table + "_body_tr_" + _key + "'>");
        if (me.checkBox) {
            field.push("<td style='vertical-align:middle;width:2%;text-align:center;'>");
            field.push("<div class='cover-checkbox' style='position:relative;width: 100%; height: 100%;top: 0px; left: 0px;'>");
            field.push("	<input id='" + me.table + "_grid_checkbox_" + _key + "' data-key='" + _key + "' type='checkbox' name='" + me.table + "_grid_checkbox' />");
            field.push("    <div style='position:absolute;z-index: 2;width: 100%; height: 100%;top: 0px; left: 0px;opacity: 1;'></div>");
            field.push("</div>");
            field.push("</td>");
        }
        field.push("<td style='vertical-align:middle;width:" + me.noWidth + "%;text-align:right;'>");
        field.push(_key);
        field.push("</td>");
        for (var f = 0; f < me.field.length; f++) {
            var _value = _map.get(me.field[f].name);
            var _style = "";
            if (me.field[f].hidden) {
                _style += "display: none;";
            }
            if (typeof me.field[f].align === "string") {
                _style += "text-align: " + me.field[f].align + ";";
            }
            field.push("<td id='" + me.table + "_body_td_" + me.field[f].name + "_" + _key + "' style='" + _style + "vertical-align:middle;width:" + me.field[f].width + "%;' >");
            if (typeof me.field[f].format === 'function') {
                field.push(me.field[f].format(_map, _key));
            } else {
                field.push(_value);
            }
            field.push("</td>");
        }
        field.push("</tr>");
        $("#" + me.table + "_body").append(field.join(""));
        if (me.checkBox) {
            $("#" + me.table + "_body_tr_" + _key).bind("click", function () {
                var par = $(this).parent();
                var _key = $(this).find("input[type='checkbox']").attr("data-key");
                var $chb = $("#" + me.table + "_grid_checkbox_" + _key);
                if ($chb.hasClass('multiple-check')) {
                    if ($chb.is(":checked")) {
                        me.keys.remove(_key);
                        $chb.prop("checked", false);
                        $(this).removeAttr("style");
                        $("#" + me.table + "_chb").prop("checked", false);
                    } else {
                        me.keys.push(_key);
                        $chb.prop("checked", true);
                        $(this).css("background-color", "#f5f5f5");
                    }
                    return;
                }
                let checkBoxes = par.find("input[name='" + me.table + "_grid_checkbox']");
                for (let i = 0; i < checkBoxes.length; i++) {
                    let check_key = $(checkBoxes[i]).attr("data-key");
                    me.keys.remove(check_key);
                    $(checkBoxes[i]).prop("checked", false);
                    $(checkBoxes[i]).closest('tr').removeAttr("style");
                }
                $("#" + me.table + "_chb").prop("checked", false);
                if ($chb.is(":checked")) {
                    // me.keys.remove(_key);
                    // $chb.prop("checked", false);
                    // $(this).removeAttr("style");
                } else {
                    me.keys = [];
                    me.keys.push(_key);
                    $chb.prop("checked", true);
                    $(this).css("background-color", "#f5f5f5");
                }
            });
            $('.cover-checkbox').on('mouseover', function () {
                $(this).find("input[type='checkbox']").addClass("multiple-check");
            })
            $('.cover-checkbox').on('mouseout', function () {
                $(this).find("input[type='checkbox']").removeClass("multiple-check");
            })
        }

    }
}());