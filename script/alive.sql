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
    nickname    varchar(20)  not null default '' comment '用户昵称',
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
    result_msg       varchar(512)  null     default '' comment '结果提示',
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
    id                    bigint        not null auto_increment primary key comment '主键',
    name                  varchar(64)   not null default '' comment '数据源名称',
    url                   varchar(1024) not null default '' comment '数据源地址',
    username              varchar(255)  not null default '' comment '用户名',
    password              varchar(255)  not null default '' comment '密码',
    is_del                tinyint       not null default 0 comment '是否删除',
    creator               bigint        null     default null comment '创建者',
    create_time           datetime      not null default current_timestamp comment '创建时间',
    updater               bigint        null     default null comment '更新者',
    update_time           datetime      not null default current_timestamp on update current_timestamp comment '更新时间'
) comment '数据源配置';