package org.springblade.modules.api.controller;

import org.springblade.core.base.controller.BladeController;
import org.springblade.core.plugins.dao.Md;
import org.springblade.core.toolbox.CMap;
import org.springblade.core.toolbox.ajax.AjaxResult;
import org.springblade.core.toolbox.grid.BladePage;
import org.springblade.modules.platform.model.Notice;
import org.springblade.modules.platform.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * api接口示例
 */
@RestController
@RequestMapping("api")
public class ApiController extends BladeController {

    @Autowired
    NoticeService service;

    /**
     * 获取单条
     * @url http://localhost:8080/api/one.json?id=7
     * @param id
     * @return
     */
    @GetMapping("one")
    public AjaxResult one(@RequestParam Integer id) {
        Notice notice = Md.selectOne("api.one", CMap.init().set("id", id), Notice.class);
        return json(notice);
    }

    /**
     * 获取多条
     * @url http://localhost:8080/api/list.json?title=通知
     * @param title
     * @return
     */
    @GetMapping("list")
    public AjaxResult list(@RequestParam(defaultValue = "", required = false) String title) {
        List<Map> list = Md.selectList("api.list", CMap.init().set("title", title), Map.class);
        return json(list);
    }

    /**
     * 获取分页
     * @url http://localhost:8080/api/page.json?title=通知
     * @param page
     * @param pageSize
     * @param title
     * @return
     */
    @GetMapping("page")
    public AjaxResult page(@RequestParam(defaultValue = "1", required = false) Integer page,
                                         @RequestParam(defaultValue = "15", required = false) Integer pageSize,
                                         @RequestParam(defaultValue = "", required = false) String title) {
        CMap param = CMap.init().set("title", title);
        BladePage<Map> list = Md.paginate("api.page", Map.class, param, page, pageSize);
        return json(list);
    }

    /**
     * 新增数据
     * @return
     */
    @PostMapping("save")
    public AjaxResult save() {
        Notice notice = mapping("notice", Notice.class);
        boolean temp = service.save(notice);
        if (temp) {
            return success("新增成功");
        } else {
            return error("新增失败");
        }
    }

    /**
     * 修改数据
     * @return
     */
    @PostMapping("update")
    public AjaxResult update() {
        Notice notice = mapping("notice", Notice.class);
        //1.使用mapper
        //NoticeMapper mapper = Md.getMapper(NoticeMapper.class);
        //boolean temp = mapper.updateTemplateById(notice) > 0;
        //2.使用sql模板
        //boolean temp = Md.update("notice.update", notice) > 0;
        //3.使用自动生成api
        boolean temp = service.update(notice);
        if (temp) {
            return success("修改成功");
        } else {
            return error("修改失败");
        }
    }

    /**
     * 删除数据
     * @param ids
     * @return
     */
    @PostMapping("remove")
    public AjaxResult remove(@RequestParam String ids) {
        int cnt = service.deleteByIds(ids);
        if (cnt > 0) {
            return success("删除成功");
        } else {
            return error("删除失败");
        }
    }


}
