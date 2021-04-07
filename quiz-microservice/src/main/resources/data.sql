-- Tournament quiz
INSERT INTO public.person (id, email, first_name, image_url, last_name, user_name) VALUES ('d72d5d78-97d7-11eb-a8b3-0242ac130003', 'amusic1@etf.unsa.ba', 'ime', null, 'prezime', 'swww');
INSERT INTO public.category (id, image_url, name) VALUES ('57464010-97d8-11eb-a8b3-0242ac130003',null, 'ime');
INSERT INTO public.quiz (id, date_created, name, time_limit, total_questions, category_id, person_id) VALUES ('1bfdd50f-90b3-4804-81f2-1062e4292508', '2021-04-07 19:43:26.218458', '2021-04-07T19:43:26.155401400', 150, 10, '57464010-97d8-11eb-a8b3-0242ac130003', 'd72d5d78-97d7-11eb-a8b3-0242ac130003');


INSERT INTO public.question (id, name, type, quiz_id) VALUES ('e400ee02-9b6d-490c-a5b8-d74b363fe160', 'How many books are in the Chronicles of Narnia series?', 0, '1bfdd50f-90b3-4804-81f2-1062e4292508');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('ab822744-664b-4f1c-aa72-c29822f4eca5', 'In the game Half-Life, which enemy is showcased as the final boss?', 0, '1bfdd50f-90b3-4804-81f2-1062e4292508');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('3119f95b-f5ab-4056-8fd5-b783a7c9ff7c', 'What is the title of song on the main menu in Halo?', 0, '1bfdd50f-90b3-4804-81f2-1062e4292508');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('300d80a3-83b6-4579-b05b-68763ee5943b', 'According to Toby Fox, what was the method to creating the initial tune for Megalovania?', 0, '1bfdd50f-90b3-4804-81f2-1062e4292508');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('940cf9a0-5606-4dad-8c2f-281a495a80ad', 'What was the cause of Marilyn Monroes suicide?', 0, '1bfdd50f-90b3-4804-81f2-1062e4292508');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('3a67429e-863b-42c6-ae30-f5a7fc7db9b0', '&quot;All the Boys&quot; by Panic! At the Disco was released as a bonus track on what album?', 0, '1bfdd50f-90b3-4804-81f2-1062e4292508');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('95bf63b8-40c3-4254-85e9-6b4e1405c0ca', 'Albert Einstein had trouble with mathematics when he was in school.', 0, '1bfdd50f-90b3-4804-81f2-1062e4292508');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('ccdac450-e9ab-4221-8663-84010519f065', 'In &quot;Halo 2&quot;, what is the name of the monitor of Installation 05?', 0, '1bfdd50f-90b3-4804-81f2-1062e4292508');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('83c7c37f-6917-4396-869d-49c6a01325f6', 'In the Zombies mode of &quot;Call of Duty: Black Ops&quot;, what is the &quot;Pack-A-Punched&quot; version of the &quot;Galil&quot; called?', 0, '1bfdd50f-90b3-4804-81f2-1062e4292508');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('2370d6ff-08cc-4c47-8313-96c5c2c4cf1d', 'What is the name of Chris&#039;s brother in &quot;Everybody Hates Chris&quot;?', 0, '1bfdd50f-90b3-4804-81f2-1062e4292508');

INSERT INTO public.answer (id, correct, name, question_id) VALUES ('e6347dd3-0eed-49b4-8c27-1d0f131ef7b0', true, '7', 'e400ee02-9b6d-490c-a5b8-d74b363fe160');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('f14c05b4-7b26-4d29-8981-5eba8f7e00db', false, '6', 'e400ee02-9b6d-490c-a5b8-d74b363fe160');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('aadebb94-c1e9-4399-b663-7c808b55d0a4', false, '8', 'e400ee02-9b6d-490c-a5b8-d74b363fe160');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('17d4ea69-d4ad-4124-97d6-6dd814ee8229', false, '5', 'e400ee02-9b6d-490c-a5b8-d74b363fe160');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('24e6bcb0-a719-4314-a706-1b031054461b', true, 'The Nihilanth', 'ab822744-664b-4f1c-aa72-c29822f4eca5');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('df071e5e-3ff3-4bee-993d-bae286fe34b2', false, 'Dr. Wallace Breen', 'ab822744-664b-4f1c-aa72-c29822f4eca5');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('d0c0f5a3-db55-41c5-8b2e-01c3e0c1809d', false, 'G-Man', 'ab822744-664b-4f1c-aa72-c29822f4eca5');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('ea91e830-dbc4-4467-a003-f39496d96557', false, 'The Gonarch', 'ab822744-664b-4f1c-aa72-c29822f4eca5');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('428fe8e5-de64-44c5-90fa-3583339c4693', true, 'Halo', '3119f95b-f5ab-4056-8fd5-b783a7c9ff7c');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('43911d1c-0081-49c7-96ed-356d32f2ada9', false, 'Opening Suite', '3119f95b-f5ab-4056-8fd5-b783a7c9ff7c');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('fdd49194-09ad-4337-9f44-b0b49a6d418e', false, 'Shadows', '3119f95b-f5ab-4056-8fd5-b783a7c9ff7c');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('6253b0a3-3f22-4b4d-aebf-4221370856bd', false, 'Suite Autumn', '3119f95b-f5ab-4056-8fd5-b783a7c9ff7c');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('09b744c4-4a33-4179-a776-1d80f2a2d6a7', true, 'Singing into a Microphone', '300d80a3-83b6-4579-b05b-68763ee5943b');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('83e8bf76-29d6-417d-b146-0706b958bab3', false, 'Playing a Piano', '300d80a3-83b6-4579-b05b-68763ee5943b');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('ca63cfca-e7d4-44c2-8ee0-f6dba3232e2f', false, 'Using a Composer Software', '300d80a3-83b6-4579-b05b-68763ee5943b');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('6c8b6687-d7f8-44b3-a629-80c392821de5', false, 'Listened to birds at the park', '300d80a3-83b6-4579-b05b-68763ee5943b');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('589ba0e3-2e7f-4489-bb44-c4b8ddebf33f', true, 'Drug Overdose', '940cf9a0-5606-4dad-8c2f-281a495a80ad');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('f553782d-d23d-4fb9-81d8-25d1a44e1f8f', false, 'Knife Attack', '940cf9a0-5606-4dad-8c2f-281a495a80ad');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('a580db12-fa4d-408e-87d5-32707553b93d', false, 'House Fire', '940cf9a0-5606-4dad-8c2f-281a495a80ad');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('4499769a-bdec-4bc1-b859-5a179c27d5e5', false, 'Gunshot', '940cf9a0-5606-4dad-8c2f-281a495a80ad');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('a15a7f6e-cc8d-4231-b7ec-ed9427515034', true, 'Too Weird To Live, Too Rare To Die!', '3a67429e-863b-42c6-ae30-f5a7fc7db9b0');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('4d60e5ac-0ee7-4084-9708-20fa435966fa', false, 'A Fever You Can&#039;t Sweat Out', '3a67429e-863b-42c6-ae30-f5a7fc7db9b0');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('3a1c9b73-4157-4511-8afd-e5767aff008c', false, 'Death Of A Bachelor', '3a67429e-863b-42c6-ae30-f5a7fc7db9b0');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('2d2e5061-ecb1-48bf-b617-7bc489015655', false, 'Vices &amp; Virtues', '3a67429e-863b-42c6-ae30-f5a7fc7db9b0');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('81f44b24-c9d0-48f9-9748-489ee43485c5', true, 'False', '95bf63b8-40c3-4254-85e9-6b4e1405c0ca');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('eae5faf7-63bc-4c79-b5f4-a9b043688863', false, 'True', '95bf63b8-40c3-4254-85e9-6b4e1405c0ca');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('4f6b7b63-404f-485a-81f3-c73ea7ebd30a', true, '2401 Penitent Tangent', 'ccdac450-e9ab-4221-8663-84010519f065');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('bdc7f312-3f53-4d8d-a1d5-7da7199821dd', false, '343 Guilty Spark', 'ccdac450-e9ab-4221-8663-84010519f065');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('49d2bd37-1312-4c73-8f94-59d034f4ffaa', false, '031 Exuberant Witness', 'ccdac450-e9ab-4221-8663-84010519f065');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('d6435622-60e5-4612-9b90-58a75c699b8d', false, '252 Biodis Expolsion', 'ccdac450-e9ab-4221-8663-84010519f065');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('50b68f42-0e5f-43da-8012-f3e5b93b1bdb', true, 'Lamentation', '83c7c37f-6917-4396-869d-49c6a01325f6');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('6236d28e-2eb8-4423-8f59-0eeec3d0211e', false, 'Galvanized Atomic Lead Insertion Liquidator', '83c7c37f-6917-4396-869d-49c6a01325f6');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('49a42672-11f2-476a-ab11-9d3ec6c10ef8', false, 'Gabig', '83c7c37f-6917-4396-869d-49c6a01325f6');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('cafa3e26-0b5b-46a3-8485-615a98a49f6c', false, 'Predictive Death Wish', '83c7c37f-6917-4396-869d-49c6a01325f6');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('aa101d3f-60bc-4086-a2a7-23b54838dad8', true, 'Drew', '2370d6ff-08cc-4c47-8313-96c5c2c4cf1d');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('182cb183-b021-4b1b-a2cf-c71561ceb8b5', false, 'Jerome', '2370d6ff-08cc-4c47-8313-96c5c2c4cf1d');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('ed5990ef-c321-48a5-8199-6b311a8b74f9', false, 'Greg', '2370d6ff-08cc-4c47-8313-96c5c2c4cf1d');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('1123c2fc-30ee-49bb-98db-40852e602e9a', false, 'Joe', '2370d6ff-08cc-4c47-8313-96c5c2c4cf1d');

INSERT INTO public.category (id, image_url, name) VALUES ('d22558ce-d8e7-400c-b37f-a6de88192fe1', null, 'Category 1');
INSERT INTO public.category (id, image_url, name) VALUES ('bfd8b999-3c43-4a3a-ad39-ce490bc86d5c', null, 'Category 2');
INSERT INTO public.category (id, image_url, name) VALUES ('fc891004-5435-4632-ad24-600eb03da5cf', null, 'Category 3');
INSERT INTO public.category (id, image_url, name) VALUES ('285d4c25-efc3-4916-bc9d-edaada54fae7', null, 'Category 4');
INSERT INTO public.category (id, image_url, name) VALUES ('f5242a3b-3076-4ad2-a422-433901ce2e39', null, 'Category 5');
INSERT INTO public.category (id, image_url, name) VALUES ('bcb18585-5dbd-44f3-a97c-7302e57a9620', null, 'Category 6');
INSERT INTO public.category (id, image_url, name) VALUES ('66028772-71af-4b15-b006-dfca38ea77b3', null, 'Quiz 5');
INSERT INTO public.category (id, image_url, name) VALUES ('97a5735c-7959-4eba-ab25-2eb3f559ec85', null, 'Quiz 7');

INSERT INTO public.person (id, email, first_name, image_url, last_name, username) VALUES ('d234091b-41f8-45a5-927a-89f88e6d5da0', 'MC@gmail.com', 'AMRA', null, 'MUSIC', 'AAA');

INSERT INTO public.quiz (id, date_created, name, time_limit, total_questions, category_id, person_id) VALUES ('a545e6a4-546e-45ad-880f-81bfda328b01', '2021-04-07 22:17:14.059577', 'Quizz 8', 23, 10, '97a5735c-7959-4eba-ab25-2eb3f559ec85', 'd234091b-41f8-45a5-927a-89f88e6d5da0');