-- Table: public.board_game

-- DROP TABLE public.board_game;

CREATE TABLE public.board_game
(
  id text NOT NULL,
  bgg_id text NOT NULL,
  CONSTRAINT board_game_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

CREATE SEQUENCE public."USERS_id_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

-- Table: public.users

-- DROP TABLE public.users;

CREATE TABLE public.users
(
  id integer NOT NULL DEFAULT nextval('"USERS_id_seq"'::regclass),
  username text,
  password text,
  email text,
  first_name text,
  last_name text,
  enabled boolean,
  bgg_username text,
  CONSTRAINT "USERS_pkey" PRIMARY KEY (id),
  CONSTRAINT "USERS_email_key" UNIQUE (email),
  CONSTRAINT "USERS_username_key" UNIQUE (username)
)
WITH (
  OIDS=FALSE
);

CREATE SEQUENCE public.user_roles_user_role_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
-- Table: public.user_roles

-- DROP TABLE public.user_roles;

CREATE TABLE public.user_roles
(
  user_role_id integer NOT NULL DEFAULT nextval('user_roles_user_role_id_seq'::regclass),
  username text NOT NULL,
  role text,
  CONSTRAINT user_roles_pkey PRIMARY KEY (user_role_id),
  CONSTRAINT user_roles_username_fkey FOREIGN KEY (username)
      REFERENCES public.users (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
  
-- Table: public.game_night

-- DROP TABLE public.game_night;

CREATE TABLE public.game_night
(
  id text NOT NULL,
  start_date date NOT NULL,
  repeat_days integer NOT NULL,
  CONSTRAINT game_night_id_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
  
-- Table: public.game_night_user

-- DROP TABLE public.game_night_user;

CREATE TABLE public.game_night_user
(
  id text NOT NULL,
  user_id integer NOT NULL,
  game_night_id text NOT NULL,
  is_owner boolean NOT NULL DEFAULT false,
  CONSTRAINT game_night_user_id_pk PRIMARY KEY (id),
  CONSTRAINT game_night_id_fk FOREIGN KEY (game_night_id)
      REFERENCES public.game_night (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT user_id_fk FOREIGN KEY (user_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
  
-- Table: public.game_night_instance

-- DROP TABLE public.game_night_instance;

CREATE TABLE public.game_night_instance
(
  id text NOT NULL,
  game_night_id text NOT NULL,
  date date NOT NULL,
  state text NOT NULL,
  CONSTRAINT game_night_instance_pk PRIMARY KEY (id),
  CONSTRAINT game_night_id_fk FOREIGN KEY (game_night_id)
      REFERENCES public.game_night (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
 
-- Table: public.game_night_instance_user

-- DROP TABLE public.game_night_instance_user;

CREATE TABLE public.game_night_instance_user
(
  user_id integer NOT NULL,
  game_night_instance_id text NOT NULL,
  has_rsvpd boolean NOT NULL DEFAULT false,
  is_coming boolean,
  has_nominated boolean NOT NULL DEFAULT false,
  has_voted boolean NOT NULL DEFAULT false,
  CONSTRAINT game_night_instance_user_pk PRIMARY KEY (user_id, game_night_instance_id),
  CONSTRAINT game_night_instance_fk FOREIGN KEY (game_night_instance_id)
      REFERENCES public.game_night_instance (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT user_fk FOREIGN KEY (user_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

  -- Table: public.game_night_instance_board_game

-- DROP TABLE public.game_night_instance_board_game;

CREATE TABLE public.game_night_instance_board_game
(
  id text NOT NULL,
  board_game_bgg_id text NOT NULL,
  game_night_instance_id text NOT NULL,
  nominated_user_id integer NOT NULL,
  average_vote double precision,
  CONSTRAINT game_night_instance_board_game_pk PRIMARY KEY (id),
  CONSTRAINT game_night_instance_fk FOREIGN KEY (game_night_instance_id)
      REFERENCES public.game_night_instance (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT user_fk FOREIGN KEY (nominated_user_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
  
-- Table: public.game_night_instance_board_game_vote

-- DROP TABLE public.game_night_instance_board_game_vote;

CREATE TABLE public.game_night_instance_board_game_vote
(
  id text NOT NULL,
  game_night_instance_board_game_id text NOT NULL,
  user_id integer NOT NULL,
  vote integer NOT NULL,
  CONSTRAINT game_night_instance_board_game_vote_pk PRIMARY KEY (id),
  CONSTRAINT game_night_instance_board_game_id_fk FOREIGN KEY (game_night_instance_board_game_id)
      REFERENCES public.game_night_instance_board_game (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT user_id_fk FOREIGN KEY (user_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

-- Table: public.registration_key

-- DROP TABLE public.registration_key;

CREATE TABLE public.registration_key
(
  registration_key text NOT NULL,
  used boolean NOT NULL DEFAULT false,
  user_role text,
  expire_date date NOT NULL,
  email text NOT NULL,
  CONSTRAINT reg_key_plk PRIMARY KEY (registration_key)
)
WITH (
  OIDS=FALSE
);

