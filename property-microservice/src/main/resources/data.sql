INSERT INTO public.comment (id, content, date_created, date_updated, person_id, quiz_id) VALUES ('531c20ca-cf09-484e-986a-177be377099e', 'Gibberish','2021-04-07 19:43:26.218458', '2021-04-07T19:43:26.155401400',  'b8181463-a15f-4eda-9d3b-e0e7ce2559a6', 'a545e6a4-546e-45ad-880f-81bfda328b01');
INSERT INTO public.comment (id, content, date_created, date_updated, person_id, quiz_id) VALUES ('c0cd372e-d61f-41e7-9c30-0106f4032c4b', 'Say what?','2021-04-07 19:43:26.218458', '2021-04-07T19:43:26.155401400',  'd72d5d78-97d7-11eb-a8b3-0242ac130003', 'f1e252f0-737e-4fb0-87a8-2cd23a18b4f9');

INSERT INTO public.score(id, correct_answers, date_scored, person_id, points,quiz_id, total_time) VALUES ('51fd3fc0-5db0-410f-8d48-b7edde1e1279', 4, '2021-04-07T19:43:26.155401400', 'b8181463-a15f-4eda-9d3b-e0e7ce2559a6', 10, 'a545e6a4-546e-45ad-880f-81bfda328b01', 15);
INSERT INTO public.score(id, correct_answers, date_scored, person_id, points,quiz_id, total_time) VALUES ('872fff2f-cf69-4a65-873b-bccbbadbf39c', 9, '2021-04-07T19:43:26.155401400', 'd72d5d78-97d7-11eb-a8b3-0242ac130003', 9, 'debb8e83-54ba-4320-b0a1-29779fc54648', 25);

INSERT INTO public.rating(id, person_id, quiz_id, rate) VALUES ('deebef14-e3ee-4212-94b8-5e60015222b2', 'd234091b-41f8-45a5-927a-89f88e6d5da0', 'a545e6a4-546e-45ad-880f-81bfda328b01', 5);
INSERT INTO public.rating(id, person_id, quiz_id, rate) VALUES ('feef2d82-e6e3-4db6-ba7b-4ce212baa909', 'b8181463-a15f-4eda-9d3b-e0e7ce2559a6', 'debb8e83-54ba-4320-b0a1-29779fc54648', 3);

INSERT INTO public.favorite(id, person_id, quiz_id) VALUES ('ab2e3119-d0ac-4ad1-86f7-09c4cefce9bf', '4c50e7ec-8754-48d4-a2fb-bf3045901340', 'a545e6a4-546e-45ad-880f-81bfda328b01');
INSERT INTO public.favorite(id, person_id, quiz_id) VALUES ('7f445dc8-f33b-40ab-ac50-88effcb37c2e', 'd72d5d78-97d7-11eb-a8b3-0242ac130003', 'debb8e83-54ba-4320-b0a1-29779fc54648');