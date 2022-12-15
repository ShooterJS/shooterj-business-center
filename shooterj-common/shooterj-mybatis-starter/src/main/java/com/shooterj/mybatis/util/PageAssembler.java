package com.shooterj.mybatis.util;

import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * Assembler class for the page.
 *
 * @author shooterj
 * @date 2022-04-04
 **/
public class PageAssembler {
    public static Page toPage(IPage iPage) {
        Page page = new Page(iPage.getRecords(), iPage.getTotal(), iPage.getSize(), iPage.getCurrent());
        return page;
    }
}
