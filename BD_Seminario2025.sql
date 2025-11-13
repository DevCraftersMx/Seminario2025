create DATABASE seminario2025;
use seminario2025;

Create table categories (
	id int not null primary key auto_increment,
    name varchar(150) not null,
    status int not null default(1)
);

create table status (
	id int not null primary key auto_increment,
    name varchar(150) not null
);

Create table priority (
	id int not null primary key auto_increment,
    name varchar(150) not null,
    status int not null default(1)
);

CREATE TABLE task (
	id int not null primary key auto_increment,
    name varchar(200) not null,
    description varchar(1000) not null,
    id_category int not null,
    id_status int not null,
    id_priority int not null,
    status int not null  default(1),
    created_at datetime default null,
    updated_at datetime,
    constraint fk_idcategory foreign key (id_category) references categories (id),
    constraint fk_idstatus foreign key (id_status) references status (id),
    constraint fk_idpriority foreign key (id_priority) references priority (id)
);

insert into categories (name) values ('Hogar'), ('Trabajo'), ('Escuela'), ('Pasatiempo');

insert into status (name) values ('Nuevo'), ('En progreso'), ('Terminado'), ('Pendiente');

INSERT INTO priority (name) VALUES ('Urgente'), ('Moderado'), ('Crítica'), ('Normal'), ('Programada'), ('Opcional');