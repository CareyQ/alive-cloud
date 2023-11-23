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
    update_time datetime     not null default current_timestamp on update current_timestamp comment '更新时间',
    unique index idx_type (type) using btree
) comment '字典类型';

create table if not exists system_dict_data
(
    id          bigint       not null auto_increment primary key comment '主键',
    label       varchar(50)  not null default '' comment '字典标签',
    value       varchar(50)  not null default '' comment '字典键值',
    dict_type   varchar(100) not null default '' comment '字典类型',
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