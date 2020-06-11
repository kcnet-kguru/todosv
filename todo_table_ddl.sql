create table todo_db.boards
(
	BOARD_ID varchar(4) not null
		primary key,
	TITLE varchar(100) null,
	BG_COLOR varchar(7) null,
	CREATED_AT timestamp default current_timestamp() not null on update current_timestamp(),
	UPDATED_AT timestamp null
);

create table todo_db.lists
(
	BOARD_ID varchar(4) not null,
	LIST_ID varchar(4) not null,
	TITLE varchar(100) not null,
	POSITION int null,
	CREATED_AT timestamp default current_timestamp() not null on update current_timestamp(),
	UPDATE_AT timestamp default '0000-00-00 00:00:00' not null,
	primary key (BOARD_ID, LIST_ID),
	constraint LISTS_boards_BOARD_ID_fk
		foreign key (BOARD_ID) references todo_db.boards (BOARD_ID)
);

create table todo_db.cards
(
	BOARD_ID varchar(4) not null,
	LIST_ID varchar(4) not null,
	CARD_ID varchar(4) not null,
	TITLE varchar(100) not null,
	DESCRIPTION varchar(2000) not null,
	POSITION int not null,
	CREATED_AT timestamp default current_timestamp() not null on update current_timestamp(),
	UPDATED_AT timestamp default '0000-00-00 00:00:00' not null,
	constraint CARDS_pk
		unique (BOARD_ID, LIST_ID, CARD_ID),
	constraint CARDS_lists_BOARD_ID_LIST_ID_fk
		foreign key (BOARD_ID, LIST_ID) references todo_db.lists (BOARD_ID, LIST_ID)
);

create table todo_db.users
(
	EMAIL varchar(100) not null
		primary key,
	PASSWORD varchar(255) not null,
	CREATED_AT timestamp default current_timestamp() not null on update current_timestamp(),
	UPDATED_AT timestamp default '0000-00-00 00:00:00' not null
);

