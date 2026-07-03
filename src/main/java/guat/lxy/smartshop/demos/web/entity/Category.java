package guat.lxy.smartshop.demos.web.entity;

import lombok.Data;

@Data
public class Category {
    private Integer id;
    private String name;
    private String descp;  // ⚠️ 必须是 descp，不是 description
}
