create table wish
(
  id        bigint not null auto_increment,
  wipe_id   bigint not null,
  subject   varchar(256) default null,
  wish_date datetime     default null,
  primary key (id)
)
