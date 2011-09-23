--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

ALTER TABLE ONLY public.parent_children DROP CONSTRAINT fk_parent_children_parent_id;
ALTER TABLE ONLY public.sequence DROP CONSTRAINT sequence_pkey;
ALTER TABLE ONLY public.parent DROP CONSTRAINT parent_pkey;
ALTER TABLE ONLY public.independetentity DROP CONSTRAINT independetentity_pkey;
ALTER TABLE ONLY public.entityrelation DROP CONSTRAINT entityrelation_pkey;
DROP TABLE public.sequence;
DROP TABLE public.parent_children;
DROP TABLE public.parent;
DROP TABLE public.independetentity;
DROP TABLE public.entityrelation;
DROP SCHEMA public;
--
-- Name: public; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA public;


--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET search_path = public, pg_catalog;

SET default_with_oids = false;

--
-- Name: entityrelation; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE entityrelation (
    id bigint NOT NULL
);


--
-- Name: independetentity; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE independetentity (
    id bigint NOT NULL
);


--
-- Name: parent; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE parent (
    id bigint NOT NULL
);


--
-- Name: parent_children; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE parent_children (
    entityrelation_id bigint,
    parent_id bigint NOT NULL
);


--
-- Name: sequence; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE sequence (
    seq_name character varying(50) NOT NULL,
    seq_count numeric(38,0)
);


--
-- Data for Name: entityrelation; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO entityrelation VALUES (1);
INSERT INTO entityrelation VALUES (2);
INSERT INTO entityrelation VALUES (3);


--
-- Data for Name: independetentity; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- Data for Name: parent; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO parent VALUES (1);
INSERT INTO parent VALUES (2);


--
-- Data for Name: parent_children; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO parent_children VALUES (1, 1);
INSERT INTO parent_children VALUES (2, 1);
INSERT INTO parent_children VALUES (3, 1);
INSERT INTO parent_children VALUES (1, 2);
INSERT INTO parent_children VALUES (2, 2);
INSERT INTO parent_children VALUES (3, 2);


--
-- Data for Name: sequence; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO sequence VALUES ('SEQ_GEN', 250);


--
-- Name: entityrelation_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY entityrelation
    ADD CONSTRAINT entityrelation_pkey PRIMARY KEY (id);


--
-- Name: independetentity_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY independetentity
    ADD CONSTRAINT independetentity_pkey PRIMARY KEY (id);


--
-- Name: parent_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY parent
    ADD CONSTRAINT parent_pkey PRIMARY KEY (id);


--
-- Name: sequence_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY sequence
    ADD CONSTRAINT sequence_pkey PRIMARY KEY (seq_name);


--
-- Name: fk_parent_children_parent_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY parent_children
    ADD CONSTRAINT fk_parent_children_parent_id FOREIGN KEY (parent_id) REFERENCES parent(id);


--
-- PostgreSQL database dump complete
--

