--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.0

-- Started on 2020-03-07 16:43:12 JST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 16386)
-- Name: items; Type: TABLE; Schema: public; Owner: postgre
--

CREATE TABLE public.items (
    item_id integer NOT NULL,
    item_name text NOT NULL,
    price integer NOT NULL
);


ALTER TABLE public.items OWNER TO postgre;

--
-- TOC entry 203 (class 1259 OID 16394)
-- Name: purchase_history; Type: TABLE; Schema: public; Owner: postgre
--

CREATE TABLE public.purchase_history (
    purchase_date_time timestamp without time zone NOT NULL,
    purchase_history_id integer NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.purchase_history OWNER TO postgre;

--
-- TOC entry 204 (class 1259 OID 16397)
-- Name: purchase_history_detail; Type: TABLE; Schema: public; Owner: postgre
--

CREATE TABLE public.purchase_history_detail (
    item_id integer NOT NULL,
    item_price integer NOT NULL,
    quantity integer NOT NULL,
    purchase_history_id integer NOT NULL
);


ALTER TABLE public.purchase_history_detail OWNER TO postgre;

--
-- TOC entry 205 (class 1259 OID 16406)
-- Name: purchase_history_purchase_history_id_seq; Type: SEQUENCE; Schema: public; Owner: postgre
--

ALTER TABLE public.purchase_history ALTER COLUMN purchase_history_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.purchase_history_purchase_history_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 10000
    CACHE 1
);


--
-- TOC entry 3194 (class 0 OID 16386)
-- Dependencies: 202
-- Data for Name: items; Type: TABLE DATA; Schema: public; Owner: postgre
--

COPY public.items (item_id, item_name, price) FROM stdin;
1111	パソコン	100000
2222	マウス	2222
3333	キーボード	5000
\.


--
-- TOC entry 3195 (class 0 OID 16394)
-- Dependencies: 203
-- Data for Name: purchase_history; Type: TABLE DATA; Schema: public; Owner: postgre
--

COPY public.purchase_history (purchase_date_time, purchase_history_id, user_id) FROM stdin;
2020-02-29 00:00:00	1	10000
2020-02-29 00:00:00	2	10000
2020-03-01 00:00:00	3	10000
2020-03-06 00:00:00	4	10000
2020-03-06 00:00:00	5	10000
2020-03-06 00:00:00	6	10000
2020-03-06 00:00:00	7	10000
2020-03-06 00:00:00	8	10000
2020-03-06 22:57:04.728	9	10000
2020-03-06 22:57:47.565	10	10000
2020-03-06 22:58:09.818	11	10000
2020-03-06 23:10:02.828	12	10000
2020-03-06 23:32:00.219	13	10000
\.


--
-- TOC entry 3196 (class 0 OID 16397)
-- Dependencies: 204
-- Data for Name: purchase_history_detail; Type: TABLE DATA; Schema: public; Owner: postgre
--

COPY public.purchase_history_detail (item_id, item_price, quantity, purchase_history_id) FROM stdin;
1111	100000	2	1
2222	2222	1	1
3333	5000	1	1
2222	2222	1	2
1111	100000	3	3
2222	2222	111	3
3333	5000	111	3
1111	100000	1	4
3333	5000	2	4
3333	5000	1	5
2222	2222	1	6
2222	2222	1	7
1111	100000	1	8
1111	100000	1	9
3333	5000	1	10
2222	2222	1	11
1111	100000	1	11
1111	100000	1	12
2222	2222	3	13
\.


--
-- TOC entry 3203 (class 0 OID 0)
-- Dependencies: 205
-- Name: purchase_history_purchase_history_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgre
--

SELECT pg_catalog.setval('public.purchase_history_purchase_history_id_seq', 13, true);


--
-- TOC entry 3067 (class 2606 OID 16393)
-- Name: items items_pkey; Type: CONSTRAINT; Schema: public; Owner: postgre
--

ALTER TABLE ONLY public.items
    ADD CONSTRAINT items_pkey PRIMARY KEY (item_id);


-- Completed on 2020-03-07 16:43:12 JST

--
-- PostgreSQL database dump complete
--

