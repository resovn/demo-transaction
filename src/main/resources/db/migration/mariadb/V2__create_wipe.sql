create table wipe
(
  id        bigint      not null auto_increment,
  lamp_id   varchar(60) not null,
  wiper     varchar(256) default null,
  used      int(11)      default null,
  capacity  int(11)      default 0,
  wipe_date datetime     default null,
  primary key (id)
)
