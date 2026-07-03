package guat.lxy.smartshop.demos.web.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Product {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer catId;          // 分类ID（对应数据库 cat_id）
    private String img;             // 图片路径（对应数据库 photo_url）
    private String descp;           // 商品描述
    private Date releaseDate;       // 上架日期
    private Category category;      // 分类对象（用于页面显示分类名称）
}