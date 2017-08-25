--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.4
-- Dumped by pg_dump version 9.6.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: book; Type: TABLE; Schema: public; Owner: dglunts
--

CREATE TABLE book (
    id bigint NOT NULL,
    author character varying(64),
    title character varying(255),
    img_id bigint NOT NULL
);


ALTER TABLE book OWNER TO dglunts;

--
-- Name: book_id_seq; Type: SEQUENCE; Schema: public; Owner: dglunts
--

CREATE SEQUENCE book_id_seq
    START WITH 1
    INCREMENT BY 4
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE book_id_seq OWNER TO dglunts;

--
-- Name: book_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: dglunts
--

ALTER SEQUENCE book_id_seq OWNED BY book.id;


--
-- Name: favorites; Type: TABLE; Schema: public; Owner: dglunts
--

CREATE TABLE favorites (
    user_id bigint NOT NULL,
    book_id bigint NOT NULL
);


ALTER TABLE favorites OWNER TO dglunts;

--
-- Name: img; Type: TABLE; Schema: public; Owner: dglunts
--

CREATE TABLE img (
    id bigint NOT NULL,
    imgpath character varying(255)
);


ALTER TABLE img OWNER TO dglunts;

--
-- Name: img_id_seq; Type: SEQUENCE; Schema: public; Owner: dglunts
--

CREATE SEQUENCE img_id_seq
    START WITH 1
    INCREMENT BY 4
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE img_id_seq OWNER TO dglunts;

--
-- Name: img_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: dglunts
--

ALTER SEQUENCE img_id_seq OWNED BY img.id;


--
-- Name: role; Type: TABLE; Schema: public; Owner: dglunts
--

CREATE TABLE role (
    id bigint NOT NULL,
    name character varying(32)
);


ALTER TABLE role OWNER TO dglunts;

--
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: dglunts
--

CREATE SEQUENCE role_id_seq
    START WITH 1
    INCREMENT BY 4
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE role_id_seq OWNER TO dglunts;

--
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: dglunts
--

ALTER SEQUENCE role_id_seq OWNED BY role.id;


--
-- Name: user_role; Type: TABLE; Schema: public; Owner: dglunts
--

CREATE TABLE user_role (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE user_role OWNER TO dglunts;

--
-- Name: users; Type: TABLE; Schema: public; Owner: dglunts
--

CREATE TABLE users (
    id bigint NOT NULL,
    username character varying(32),
    password character varying(255)
);


ALTER TABLE users OWNER TO dglunts;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: dglunts
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 4
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_seq OWNER TO dglunts;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: dglunts
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- Name: book id; Type: DEFAULT; Schema: public; Owner: dglunts
--

ALTER TABLE ONLY book ALTER COLUMN id SET DEFAULT nextval('book_id_seq'::regclass);


--
-- Name: img id; Type: DEFAULT; Schema: public; Owner: dglunts
--

ALTER TABLE ONLY img ALTER COLUMN id SET DEFAULT nextval('img_id_seq'::regclass);


--
-- Name: role id; Type: DEFAULT; Schema: public; Owner: dglunts
--

ALTER TABLE ONLY role ALTER COLUMN id SET DEFAULT nextval('role_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: dglunts
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- Data for Name: book; Type: TABLE DATA; Schema: public; Owner: dglunts
--

COPY book (id, author, title, img_id) FROM stdin;
1	Armin Fuchs	Non-linear Dynamics in Complex Systems	1
5	Burton Zwiebach	A First Course in String Theory	5
9	jobs@perfekt.software	Java EE Web Application	9
13	David Deutsh	The Fabric of Reality	17
17	Lean-Pierre Serre	Lee Algebras and Lee Groups	21
21	Joshua Bloch	Effective Java	25
29	Craig Walles	Spring Boot in Action	29
33	Питер Олвер	Применение групп Ли к дифференциальным уравнениям	1
41	Эдуардо Керлот	Словарь символов	33
45	Жак ЛеГофф	Рождение Чистилища	37
49	Мишель Фуко	История безумия в классическу эпоху	41
53	Кон-Винер	История стилей изобразительного тскусства	45
57	Brian Goetz	Java Concurrency in Practice	49
61	Corbet,Rubini,Hartman	Linux Device Drivers	1
69	Роджер Пенроуз	Тени разума	13
73	Уильям Моррис	Основания теории знаков	13
81	Русский народ	Похождения Штирлица	53
77	В.Успенский	Теорема Геделя о неполноте	13
85	Hows, Membry,Hawkins	The Definitive guide to MongoDb	57
89	Ирина Пигулевская	Мифы и боги древних славян	61
97	Жак Маржерет	Состояние Российской Империи	65
101	Александр Морэ	Египетские Мистерии	77
105	Владимир Булдаков	Утопия,агрессиябвласть	81
109	Самюэль Хаттингтон	Столкновение цивилизаций	69
113	Платон	Государстао	73
121	Игорь Губерман	Рогулки вокруг барака	85
\.


--
-- Name: book_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dglunts
--

SELECT pg_catalog.setval('book_id_seq', 121, true);


--
-- Data for Name: favorites; Type: TABLE DATA; Schema: public; Owner: dglunts
--

COPY favorites (user_id, book_id) FROM stdin;
5	1
5	13
9	5
9	29
9	17
9	41
9	45
5	81
9	81
9	77
5	61
1	61
33	41
33	29
33	45
17	17
17	1
17	49
13	29
13	77
13	33
17	61
37	49
37	61
37	17
13	5
13	81
\.


--
-- Data for Name: img; Type: TABLE DATA; Schema: public; Owner: dglunts
--

COPY img (id, imgpath) FROM stdin;
9	/resources/img/thumb4.png
21	/resources/img/LeeGroups.png
1	/resources/img/thumb1.png
57	/resources/img/Mongo.png
61	/resources/img/Slav.png
65	/resources/img/Margeret.png
69	/resources/img/Crypto-1.png
73	/resources/img/Moscow.png
77	/resources/img/Egypt.png
81	/resources/img/Buld.png
85	/resources/img/Guber.png
29	/resources/img/SpringInAction.png
41	/resources/img/Mad.png
37	/resources/img/LeGoff.png
45	/resources/img/ArtStyles.png
25	/resources/img/EffectiveJava.png
33	/resources/img/SlovarSimvolov.png
17	/resources/img/RealityFabric.png
49	/resources/img/JavaConcurrency.png
53	/resources/img/Shtir.png
13	/resources/img/thumb2.png
5	/resources/img/thumb3.png
\.


--
-- Name: img_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dglunts
--

SELECT pg_catalog.setval('img_id_seq', 85, true);


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: dglunts
--

COPY role (id, name) FROM stdin;
1	ROLE_ADMIN
5	ROLE_USER
\.


--
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dglunts
--

SELECT pg_catalog.setval('role_id_seq', 9, true);


--
-- Data for Name: user_role; Type: TABLE DATA; Schema: public; Owner: dglunts
--

COPY user_role (user_id, role_id) FROM stdin;
1	1
5	5
9	5
13	5
17	5
33	5
37	5
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: dglunts
--

COPY users (id, username, password) FROM stdin;
1	admin	21232f297a57a5a743894a0e4a801fc3
5	Petya	7e102b51b4a538a84689c35b9818a39e
9	Masha	68c52c49d8983edeb5a488b3812ce678
13	Vasya	96932f68a34ac08a6c92ed8db20d2ee3
17	Dunya	a50370f2b57fb982e42513ad3bb69aa0
33	Manya	29e6e526b4a635ab63b50b119e6e75ff
37	Anfisa	9a1467c03f6d81c977cd2c4d4380a78f
\.


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: dglunts
--

SELECT pg_catalog.setval('users_id_seq', 37, true);


--
-- Name: book author_title_uniq; Type: CONSTRAINT; Schema: public; Owner: dglunts
--

ALTER TABLE ONLY book
    ADD CONSTRAINT author_title_uniq UNIQUE (author, title);


--
-- Name: book book_pkey; Type: CONSTRAINT; Schema: public; Owner: dglunts
--

ALTER TABLE ONLY book
    ADD CONSTRAINT book_pkey PRIMARY KEY (id);


--
-- Name: img img_pkey; Type: CONSTRAINT; Schema: public; Owner: dglunts
--

ALTER TABLE ONLY img
    ADD CONSTRAINT img_pkey PRIMARY KEY (id);


--
-- Name: role name_uniq; Type: CONSTRAINT; Schema: public; Owner: dglunts
--

ALTER TABLE ONLY role
    ADD CONSTRAINT name_uniq UNIQUE (name);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: dglunts
--

ALTER TABLE ONLY role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: user_role user_role_uniq; Type: CONSTRAINT; Schema: public; Owner: dglunts
--

ALTER TABLE ONLY user_role
    ADD CONSTRAINT user_role_uniq UNIQUE (user_id, role_id);


--
-- Name: users username_uniq; Type: CONSTRAINT; Schema: public; Owner: dglunts
--

ALTER TABLE ONLY users
    ADD CONSTRAINT username_uniq UNIQUE (username);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: dglunts
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: book book_img_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: dglunts
--

ALTER TABLE ONLY book
    ADD CONSTRAINT book_img_id_fkey FOREIGN KEY (img_id) REFERENCES img(id) ON DELETE CASCADE;


--
-- Name: favorites favorites_book_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: dglunts
--

ALTER TABLE ONLY favorites
    ADD CONSTRAINT favorites_book_id_fkey FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE;


--
-- Name: favorites favorites_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: dglunts
--

ALTER TABLE ONLY favorites
    ADD CONSTRAINT favorites_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;


--
-- Name: user_role user_role_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: dglunts
--

ALTER TABLE ONLY user_role
    ADD CONSTRAINT user_role_role_id_fkey FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE;


--
-- Name: user_role user_role_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: dglunts
--

ALTER TABLE ONLY user_role
    ADD CONSTRAINT user_role_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

