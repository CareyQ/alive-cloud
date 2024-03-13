create database alive;
use alive;

# system
create table if not exists system_user
(
    id          bigint       not null auto_increment primary key comment '主键',
    username    varchar(20)  not null comment '用户名',
    nickname    varchar(20)  not null comment '昵称',
    password    varchar(100) not null comment '密码',
    remark      varchar(500) null     default null comment '备注',
    dept_id     bigint       null     default null comment '部门 ID',
    post_ids    varchar(255) null     default null comment '岗位 ID',
    gender      tinyint      not null default 0 comment '性别',
    email       varchar(50)  null     default '' comment '邮箱',
    mobile      varchar(11)  null     default '' comment '手机号码',
    avatar      varchar(512) null     default '' comment '头像地址',
    status      tinyint      not null default 1 comment '状态，0停用 1正常',
    login_ip    varchar(50)  null     default '' comment '最后登录 IP',
    login_time  datetime     null     default null comment '最后登录时间',
    is_del      tinyint      not null default 0 comment '是否删除',
    creator     bigint       null     default null comment '创建者',
    create_time datetime     not null default current_timestamp comment '创建时间',
    updater     bigint       null     default null comment '更新者',
    update_time datetime     not null default current_timestamp on update current_timestamp comment '更新时间',
    unique index idx_username (username) using btree,
    unique index idx_mobile (mobile) using btree
) comment '系统用户';

create table if not exists system_dept
(
    id          bigint       not null auto_increment primary key comment '主键',
    name        varchar(20)  not null comment '部门名称',
    parent_id   bigint       not null default 0 comment '父级部门 ID',
    sort        int          not null default 0 comment '排序',
    manager_id  int          null     default null comment '负责人 ID',
    mobile      varchar(11)  null     default null comment '联系电话',
    remark      varchar(500) null     default null comment '备注',
    status      tinyint      not null default 1 comment '状态，1正常 0停用',
    is_del      tinyint      not null default 0 comment '是否删除',
    creator     bigint       null     default null comment '创建者',
    create_time datetime     not null default current_timestamp comment '创建时间',
    updater     bigint       null     default null comment '更新者',
    update_time datetime     not null default current_timestamp on update current_timestamp comment '更新时间'
) comment '部门管理';

create table if not exists system_post
(
    id          bigint       not null auto_increment primary key comment '主键',
    name        varchar(20)  not null comment '岗位名称',
    remark      varchar(500) null     default null comment '备注',
    status      tinyint      not null default 1 comment '状态，0停用 1正常',
    is_del      tinyint      not null default 0 comment '是否删除',
    creator     bigint       null     default null comment '创建者',
    create_time datetime     not null default current_timestamp comment '创建时间',
    updater     bigint       null     default null comment '更新者',
    update_time datetime     not null default current_timestamp on update current_timestamp comment '更新时间'
) comment '岗位信息';

create table if not exists system_menu
(
    id             bigint       not null auto_increment primary key comment '主键',
    name           varchar(20)  not null default '' comment '菜单名称',
    permission     varchar(100) not null default '' comment '权限标识',
    type           int          not null comment '菜单类型',
    parent_id      bigint       not null default 0 comment '父级部门 ID',
    sort           int          not null default 0 comment '排序',
    path           varchar(200) not null default '' comment '路由地址',
    icon           varchar(100) not null default '#' comment '菜单图标',
    component      varchar(255) null     default null comment '组件路径',
    component_name varchar(255) null     default null comment '组件名',
    status         tinyint      not null default 1 comment '菜单状态',
    keep_alive     tinyint      not null default 1 comment '是否缓存',
    visible        tinyint      not null default 1 comment '是否可见',
    is_del         tinyint      not null default 0 comment '是否删除',
    creator        bigint       null     default null comment '创建者',
    create_time    datetime     not null default current_timestamp comment '创建时间',
    updater        bigint       null     default null comment '更新者',
    update_time    datetime     not null default current_timestamp on update current_timestamp comment '更新时间'
) comment '菜单权限';

create table if not exists system_dict_type
(
    id          bigint       not null auto_increment primary key comment '主键',
    name        varchar(20)  not null default '' comment '字典名称',
    type        varchar(100) not null default '' comment '字典类型',
    status      tinyint      not null default 1 comment '状态',
    is_del      tinyint      not null default 0 comment '是否删除',
    creator     bigint       null     default null comment '创建者',
    create_time datetime     not null default current_timestamp comment '创建时间',
    updater     bigint       null     default null comment '更新者',
    update_time datetime     not null default current_timestamp on update current_timestamp comment '更新时间'
) comment '字典类型';

create table if not exists system_dict_data
(
    id          bigint       not null auto_increment primary key comment '主键',
    label       varchar(50)  not null default '' comment '字典标签',
    value       varchar(50)  not null default '' comment '字典键值',
    dict_type   varchar(100) not null default '' comment '字典类型',
    color_type  varchar(20)  not null default '' comment '颜色类型',
    remark      varchar(500) not null default '' comment '字典类型',
    status      tinyint      not null default 1 comment '状态',
    is_del      tinyint      not null default 0 comment '是否删除',
    creator     bigint       null     default null comment '创建者',
    create_time datetime     not null default current_timestamp comment '创建时间',
    updater     bigint       null     default null comment '更新者',
    update_time datetime     not null default current_timestamp on update current_timestamp comment '更新时间'
) comment '字典数据';

create table if not exists system_role
(
    id          bigint       not null auto_increment primary key comment '主键',
    name        varchar(20)  not null default '' comment '角色名称',
    code        varchar(50)  not null default '' comment '角色标识',
    remark      varchar(500) not null default '' comment '字典类型',
    is_default  tinyint      not null default 0 comment '是否是默认',
    is_del      tinyint      not null default 0 comment '是否删除',
    creator     bigint       null     default null comment '创建者',
    create_time datetime     not null default current_timestamp comment '创建时间',
    updater     bigint       null     default null comment '更新者',
    update_time datetime     not null default current_timestamp on update current_timestamp comment '更新时间'
) comment '角色信息';

create table if not exists system_role_menu
(
    id          bigint   not null auto_increment primary key comment '主键',
    role_id     bigint   not null comment '角色 ID',
    menu_id     bigint   not null comment '菜单 ID',
    is_del      tinyint  not null default 0 comment '是否删除',
    creator     bigint   null     default null comment '创建者',
    create_time datetime not null default current_timestamp comment '创建时间',
    updater     bigint   null     default null comment '更新者',
    update_time datetime not null default current_timestamp on update current_timestamp comment '更新时间'
) comment '角色菜单关联';

create table if not exists system_role_user
(
    id          bigint   not null auto_increment primary key comment '主键',
    role_id     bigint   not null comment '角色 ID',
    user_id     bigint   not null comment '用户 ID',
    is_del      tinyint  not null default 0 comment '是否删除',
    creator     bigint   null     default null comment '创建者',
    create_time datetime not null default current_timestamp comment '创建时间',
    updater     bigint   null     default null comment '更新者',
    update_time datetime not null default current_timestamp on update current_timestamp comment '更新时间'
) comment '角色用户关联';


create table if not exists infra_login_log
(
    id          bigint       not null auto_increment primary key comment '主键',
    type        int          not null comment '日志类型',
    trace_id    varchar(64)  not null default '' comment '链路追踪编号',
    username    varchar(20)  not null default '' comment '用户名',
    result      int          not null comment '登录结果',
    ip          varchar(50)  not null default '' comment '登录 IP',
    ip_info     varchar(100) not null default '' comment '登录 IP 信息',
    device      varchar(100) not null default '' comment '设备信息',
    is_del      tinyint      not null default 0 comment '是否删除',
    creator     bigint       null     default null comment '创建者',
    create_time datetime     not null default current_timestamp comment '创建时间',
    updater     bigint       null     default null comment '更新者',
    update_time datetime     not null default current_timestamp on update current_timestamp comment '更新时间'
) comment '登录日志';

create table if not exists infra_operate_log
(
    id               bigint        not null auto_increment primary key comment '主键',
    trace_id         varchar(64)   not null default '' comment '链路追踪编号',
    nickname         varchar(20)   not null default '' comment '用户昵称',
    user_type        tinyint       not null default 0 comment '用户类型',
    module           varchar(50)   not null comment '模块',
    name             varchar(50)   not null comment '操作名称',
    type             bigint        not null default 0 comment '操作类型',
    content          varchar(2000) not null default '' comment '操作内容',
    extra            varchar(512)  not null default '' comment '拓展字段',
    request_method   varchar(16)   null     default '' comment '请求方法名',
    request_url      varchar(255)  null     default '' comment '请求地址',
    ip               varchar(50)   null     default null comment 'IP',
    device           varchar(200)  null     default null comment '设备名',
    java_method      varchar(512)  not null default '' comment 'Java 方法名',
    java_method_args varchar(8000) null     default '' comment 'Java 方法的参数',
    start_time       datetime      not null comment '操作时间',
    duration         int           not null comment '执行时长',
    result_code      int           not null default 0 comment '结果码',
    result_msg       varchar(4000) null     default '' comment '结果提示',
    result_data      varchar(4000) null     default '' comment '结果数据',
    is_del           tinyint       not null default 0 comment '是否删除',
    creator          bigint        null     default null comment '创建者',
    create_time      datetime      not null default current_timestamp comment '创建时间',
    updater          bigint        null     default null comment '更新者',
    update_time      datetime      not null default current_timestamp on update current_timestamp comment '更新时间'
) comment '操作日志';

create table if not exists infra_error_log
(
    id                    bigint        not null auto_increment primary key comment '主键',
    trace_id              varchar(64)   not null default '' comment '链路追踪编号',
    nickname              varchar(20)   not null default '' comment '用户昵称',
    user_type             tinyint       not null default 0 comment '用户类型',
    request_method        varchar(16)   null     default '' comment '请求方法名',
    request_url           varchar(255)  null     default '' comment '请求地址',
    request_params        varchar(5000) null     default '' comment '请求参数',
    request_curl          varchar(5000) null     default '' comment '请求 curl',
    ip                    varchar(50)   null     default null comment 'IP',
    device                varchar(200)  null     default null comment '设备名',
    ex_name               varchar(128)  not null default '' comment '异常名，Throwable.getClass() 类全名',
    ex_message            text          not null comment '异常消息',
    ex_root_cause_message text          not null comment '异常导致的根消息',
    ex_stack_trace        text          not null comment '异常的栈轨迹',
    process_status        tinyint       not null default 0 comment '处理状态',
    process_time          datetime      null     default null comment '处理时间',
    process_user_id       bigint        null     default null comment '处理人',
    is_del                tinyint       not null default 0 comment '是否删除',
    creator               bigint        null     default null comment '创建者',
    create_time           datetime      not null default current_timestamp comment '创建时间',
    updater               bigint        null     default null comment '更新者',
    update_time           datetime      not null default current_timestamp on update current_timestamp comment '更新时间'
) comment '异常错误日志';

create table if not exists infra_data_source_config
(
    id          bigint        not null auto_increment primary key comment '主键',
    name        varchar(64)   not null default '' comment '数据源名称',
    url         varchar(1024) not null default '' comment '数据源地址',
    username    varchar(255)  not null default '' comment '用户名',
    password    varchar(255)  not null default '' comment '密码',
    is_del      tinyint       not null default 0 comment '是否删除',
    creator     bigint        null     default null comment '创建者',
    create_time datetime      not null default current_timestamp comment '创建时间',
    updater     bigint        null     default null comment '更新者',
    update_time datetime      not null default current_timestamp on update current_timestamp comment '更新时间'
) comment '数据源配置';

create table if not exists infra_codegen_table
(
    id                    bigint       not null auto_increment primary key comment '主键',
    data_source_config_id bigint       not null comment '数据源配置 ID',
    scene                 int          not null default 1 comment '场景',
    table_name            varchar(200) not null default '' comment '表名称',
    table_comment         varchar(200) not null default '' comment '表描述',
    module_name           varchar(30)  not null default '' comment '模块名',
    business_name         varchar(30)  not null default '' comment '业务名',
    class_name            varchar(100) not null default '' comment '类名称',
    class_comment         varchar(30)  not null default '' comment '类描述',
    author                varchar(50)  not null default '' comment '作者',
    is_del                tinyint      not null default 0 comment '是否删除',
    creator               bigint       null     default null comment '创建者',
    create_time           datetime     not null default current_timestamp comment '创建时间',
    updater               bigint       null     default null comment '更新者',
    update_time           datetime     not null default current_timestamp on update current_timestamp comment '更新时间'
) comment '代码生成表定义';

create table if not exists infra_codegen_column
(
    id               bigint       not null auto_increment primary key comment '主键',
    table_id         bigint       not null comment '表 ID',
    column_name      varchar(200) not null default '' comment '字段名称',
    data_type        varchar(100) not null default '' comment '字段类型',
    column_comment   varchar(200) not null default '' comment '字段描述',
    nullable         tinyint      not null comment '是否可以为空',
    primary_key      tinyint      not null comment '是否主键',
    auto_increment   tinyint      not null comment '是否自增',
    java_type        varchar(32)  not null default '' comment 'Java 属性类型',
    java_field       varchar(64)  not null default '' comment 'Java 属性名',
    dict_type        varchar(200) not null default '' comment '字典类型',
    create_operation tinyint      not null default 0 comment '是否是创建操作字段',
    update_operation tinyint      not null default 0 comment '是否是更新操作字段',
    query_result     tinyint      not null default 0 comment '是否是查询结果字段',
    query_condition  tinyint      not null default 0 comment '是否是查询条件字段',
    query_type       varchar(32)  not null comment '查询时作为条件的类型',
    html_type        varchar(32)  not null comment '前端显示类型',
    is_del           tinyint      not null default 0 comment '是否删除',
    creator          bigint       null     default null comment '创建者',
    create_time      datetime     not null default current_timestamp comment '创建时间',
    updater          bigint       null     default null comment '更新者',
    update_time      datetime     not null default current_timestamp on update current_timestamp comment '更新时间'
) comment '代码生成表字段';

create table if not exists infra_file
(
    id          bigint        not null auto_increment primary key comment '主键',
    config_id   bigint        not null comment '配置编号',
    name        varchar(250)  not null default '' comment '文件名',
    folder      varchar(50)   not null default '' comment '目录',
    path        varchar(512)  not null default '' comment '文件路径',
    url         varchar(1024) not null default '' comment '文件 URL',
    type        varchar(128)  not null default '' comment '文件类型',
    size        int           not null default 0 comment '文件大小',
    is_del      tinyint       not null default 0 comment '是否删除',
    creator     bigint        null     default null comment '创建者',
    create_time datetime      not null default current_timestamp comment '创建时间',
    updater     bigint        null     default null comment '更新者',
    update_time datetime      not null default current_timestamp on update current_timestamp comment '更新时间'
) comment '文件';

create table if not exists infra_oss_config
(
    id          bigint       not null auto_increment primary key comment '主键',
    name        varchar(50)  not null default '' comment '配置名称',
    access_key  varchar(200) not null comment 'accessKey',
    secret_key  varchar(200) not null comment '秘钥',
    bucket      varchar(200) not null comment '桶名称',
    endpoint    varchar(200) not null comment '访问站点',
    region      varchar(200) not null comment '域',
    master      tinyint(1)   not null default 0 comment '是否为主配置',
    is_del      tinyint      not null default 0 comment '是否删除',
    creator     bigint       null     default null comment '创建者',
    create_time datetime     not null default current_timestamp comment '创建时间',
    updater     bigint       null     default null comment '更新者',
    update_time datetime     not null default current_timestamp on update current_timestamp comment '更新时间'
) comment '对象存储配置';

# mall
create table if not exists product_category
(
    id          bigint       not null auto_increment primary key comment '主键',
    parent_id   bigint       not null default 0 comment '父级分类 ID',
    name        varchar(255) not null comment '分类名称',
    sort        int          not null default 0 comment '排序',
    icon        varchar(255) null     default '' comment '图标',
    status      tinyint      not null default 1 comment '状态，0停用 1正常',
    is_del      tinyint      not null default 0 comment '是否删除',
    creator     bigint       null     default null comment '创建者',
    create_time datetime     not null default current_timestamp comment '创建时间',
    updater     bigint       null     default null comment '更新者',
    update_time datetime     not null default current_timestamp on update current_timestamp comment '更新时间'
) comment '商品分类';

create table if not exists product_brand
(
    id          bigint        not null auto_increment primary key comment '主键',
    name        varchar(255)  not null comment '品牌名称',
    sort        int           not null default 0 comment '排序',
    logo        varchar(255)  not null default '' comment '品牌 logo',
    description varchar(1024) null     default null comment '品牌描述',
    status      tinyint       not null default 1 comment '状态，0停用 1正常',
    is_del      tinyint       not null default 0 comment '是否删除',
    creator     bigint        null     default null comment '创建者',
    create_time datetime      not null default current_timestamp comment '创建时间',
    updater     bigint        null     default null comment '更新者',
    update_time datetime      not null default current_timestamp on update current_timestamp comment '更新时间'
) comment '商品品牌';

create table if not exists product_attribute_group
(
    id          bigint       not null auto_increment primary key comment '主键',
    category_id bigint       not null comment '分类 ID',
    name        varchar(255) not null comment '属性分组名称',
    sort        int          not null default 0 comment '排序',
    is_del      tinyint      not null default 0 comment '是否删除',
    creator     bigint       null     default null comment '创建者',
    create_time datetime     not null default current_timestamp comment '创建时间',
    updater     bigint       null     default null comment '更新者',
    update_time datetime     not null default current_timestamp on update current_timestamp comment '更新时间'
) comment '商品属性分组';

create table if not exists product_attribute
(
    id             bigint       not null auto_increment primary key comment '主键',
    group_id       bigint       null     default null comment '所属分组，类型为参数时',
    type           int          not null default 0 comment '属性类型，0规格 1参数',
    name           varchar(255) not null comment '属性名称',
    select_type    tinyint      not null default 0 comment '属性选择类型，0唯一 1单选 2多选',
    input_type     tinyint      not null default 0 comment '属性录入方式，0手工录入 1从列表中选取',
    input_list     varchar(255) null     default null comment '可选值列表，以逗号隔开',
    sort           int          not null default 0 comment '排序',
    filter_type    tinyint      not null default 0 comment '分类筛选样式，0普通 1颜色',
    search_type    tinyint      not null default 0 comment '检索类型，0不需要进行检索 1关键字检索 2范围检索',
    related_status tinyint      not null default 0 comment '相同属性产品是否关联，0不关联 1关联',
    addition       tinyint      not null default 0 comment '支持新增',
    is_del         tinyint      not null default 0 comment '是否删除',
    creator        bigint       null     default null comment '创建者',
    create_time    datetime     not null default current_timestamp comment '创建时间',
    updater        bigint       null     default null comment '更新者',
    update_time    datetime     not null default current_timestamp on update current_timestamp comment '更新时间'
) comment '商品属性';

create table if not exists product_attribute_value
(
    id           bigint       not null auto_increment primary key comment '主键',
    attribute_id bigint       not null comment '属性 ID',
    value        varchar(255) not null comment '属性值',
    sort         int          not null default 0 comment '排序',
    is_del       tinyint      not null default 0 comment '是否删除',
    creator      bigint       null     default null comment '创建者',
    create_time  datetime     not null default current_timestamp comment '创建时间',
    updater      bigint       null     default null comment '更新者',
    update_time  datetime     not null default current_timestamp on update current_timestamp comment '更新时间'
) comment '商品属性值';

create table if not exists product_attribute_value
(
    id           bigint       not null auto_increment primary key comment '主键',
    attribute_id bigint       not null comment '属性 ID',
    value        varchar(255) not null comment '属性值',
    sort         int          not null default 0 comment '排序',
    is_del       tinyint      not null default 0 comment '是否删除',
    creator      bigint       null     default null comment '创建者',
    create_time  datetime     not null default current_timestamp comment '创建时间',
    updater      bigint       null     default null comment '更新者',
    update_time  datetime     not null default current_timestamp on update current_timestamp comment '更新时间'
) comment '商品属性值';

create table if not exists product
(
    id                 bigint         not null auto_increment primary key comment '编号',
    category_id        bigint         not null comment '所属分类',
    brand_id           bigint         not null comment '所属品牌',
    attribute_group_id bigint         not null comment '属性分组',
    sn_code            varchar(64)    not null comment '商品编号',
    name               varchar(255)   not null comment '商品名称',
    pic                varchar(255)   not null comment '商品封面图片',
    status             int            not null default 1 comment '状态，0下架 1上架',
    sort               int            not null default 0 comment '排序',
    sales_volume       int            not null default 0 comment '销量',
    price              decimal(10, 2) not null default 0.00 comment '价格',
    market_price       decimal(10, 2) not null default 0.00 comment '市场价',
    stock              int            not null default 0 comment '库存',
    unit               varchar(10)    not null default '' comment '单位',
    detail_html        text           null comment '商品详情',
    detail_mobile_html text           null comment '移动端商品详情',
    gift_point         int            not null default 0 comment '赠送积分',
    gift_growth        int            not null default 0 comment '赠送成长值',
    use_point_limit    int            not null default 0 comment '限制使用的积分数',
    sub_title          varchar(255)   not null default '' comment '副标题',
    keyword            varchar(255)   not null default '' comment '关键字',
    intro              varchar(255)   not null default '' comment '简介',
    is_del             tinyint        not null default 0 comment '是否删除',
    creator            bigint         null     default null comment '创建者',
    create_time        datetime       not null default current_timestamp comment '创建时间',
    updater            bigint         null     default null comment '更新者',
    update_time        datetime       not null default current_timestamp on update current_timestamp comment '更新时间',
    index idx_categoryId (category_id) using btree,
    index idx_brandId (brand_id) using btree,
    index idx_attributeGroupId (attribute_group_id) using btree,
    index idx_snCode (sn_code) using btree
) comment '商品信息';

create table if not exists product_sku
(
    id           bigint         not null auto_increment primary key comment '编号',
    product_id   bigint         not null comment '商品 ID',
    sku_code     varchar(255)   not null comment 'SKU 编码',
    sales_volume int            not null default 0 comment '销量',
    price        decimal(10, 2) not null default 0.00 comment '价格',
    market_price decimal(10, 2) not null default 0.00 comment '市场价',
    stock        int            not null default 0 comment '库存',
    album_pics   varchar(3000)  not null comment 'SKU 图片数组',
    weight       decimal(10, 2) null     default null comment '商品重量，千克',
    volume       decimal(10, 2) null     default null comment '商品体积，立方米',
    is_del       tinyint        not null default 0 comment '是否删除',
    creator      bigint         null     default null comment '创建者',
    create_time  datetime       not null default current_timestamp comment '创建时间',
    updater      bigint         null     default null comment '更新者',
    update_time  datetime       not null default current_timestamp on update current_timestamp comment '更新时间',
    index idx_productId (product_id) using btree
) comment '商品 SKU 信息';