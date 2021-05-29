-- Tournament quiz
INSERT INTO public.quiz (id, date_created, name, time_limit, total_questions, category_id, person_id) VALUES ('1bfdd50f-90b3-4804-81f2-1062e4292508', '2021-04-07 19:43:26.218458', 'Fun quiz', 150000, 6, null, 'd234091b-41f8-45a5-927a-89f88e6d5da0');
INSERT INTO public.quiz (id, date_created, name, person_id, time_limit, total_questions, tournament_id, category_id) VALUES ('a545e6a4-546e-45ad-880f-81bfda328b01', '2021-04-07 22:17:14.059577', 'Quiz for everyone', 'd72d5d78-97d7-11eb-a8b3-0242ac130003', 230000, 5, null, null);
INSERT INTO public.quiz (id, date_created, name, person_id, time_limit, total_questions, tournament_id, category_id) VALUES ('bd7d31f9-0df5-4fb4-b1c9-85a26a474b83', '2021-04-08 22:17:14.059577', 'Quiz not for everyone', 'd72d5d78-97d7-11eb-a8b3-0242ac130003', 560000, 4, null, null);
INSERT INTO public.quiz (id, date_created, name, person_id, time_limit, total_questions, tournament_id, category_id) VALUES ('d42805e2-73c3-4573-b9e2-9c820f3436dc', '2021-04-09 22:27:14.059577', 'Easy quiz', 'd72d5d78-97d7-11eb-a8b3-0242ac130003', 880000, 1, null, null);
INSERT INTO public.quiz (id, date_created, name, person_id, time_limit, total_questions, tournament_id, category_id) VALUES ('d770dea6-755e-4b01-b4b1-b844d3dd6a28', '2021-04-09 22:17:14.059577', ':)', 'd72d5d78-97d7-11eb-a8b3-0242ac130003', 1000000, 1, null, null);
INSERT INTO public.quiz (id, date_created, name, person_id, time_limit, total_questions, tournament_id, category_id) VALUES ('da5b7318-f10c-4deb-b2cd-021779d10cea', '2021-04-09 22:47:14.059577', 'Easy', 'd72d5d78-97d7-11eb-a8b3-0242ac130003', 837000, 3, null, null);
INSERT INTO public.quiz (id, date_created, name, person_id, time_limit, total_questions, tournament_id, category_id) VALUES ('536d15d9-a539-448f-948a-21bc88c33663', '2021-04-10 22:17:14.059577', 'Example quiz', 'd72d5d78-97d7-11eb-a8b3-0242ac130003', 390000, 3, null, null);

INSERT INTO public.question (id, name, type, quiz_id) VALUES ('e400ee02-9b6d-490c-a5b8-d74b363fe160', 'How many books are in the Chronicles of Narnia series?', 0, '1bfdd50f-90b3-4804-81f2-1062e4292508');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('536d15d9-a539-448f-948a-21bc88c33663', 'How many books are in the Chronicles of Narnia series?', 0, 'a545e6a4-546e-45ad-880f-81bfda328b01');

INSERT INTO public.question (id, name, type, quiz_id) VALUES ('ab822744-664b-4f1c-aa72-c29822f4eca5', 'In the game Half-Life, which enemy is showcased as the final boss?', 0, '1bfdd50f-90b3-4804-81f2-1062e4292508');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('560565b7-65fe-4d77-8d80-a085ebdc3734', 'In the game Half-Life, which enemy is showcased as the final boss?', 0, 'bd7d31f9-0df5-4fb4-b1c9-85a26a474b83');

INSERT INTO public.question (id, name, type, quiz_id) VALUES ('3119f95b-f5ab-4056-8fd5-b783a7c9ff7c', 'What is the title of song on the main menu in Halo?', 0, '1bfdd50f-90b3-4804-81f2-1062e4292508');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('1060c6a1-6b09-4572-8651-8fd74a1b00c3', 'What is the title of song on the main menu in Halo?', 0, 'da5b7318-f10c-4deb-b2cd-021779d10cea');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('13dac7b8-c488-4a38-9624-e68fe68403c3', 'What is the title of song on the main menu in Halo?', 0, 'bd7d31f9-0df5-4fb4-b1c9-85a26a474b83');

INSERT INTO public.question (id, name, type, quiz_id) VALUES ('300d80a3-83b6-4579-b05b-68763ee5943b', 'According to Toby Fox, what was the method to creating the initial tune for Megalovania?', 0, '1bfdd50f-90b3-4804-81f2-1062e4292508');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('91501ff0-776c-41cf-8508-244fd366d17a', 'According to Toby Fox, what was the method to creating the initial tune for Megalovania?', 0, 'da5b7318-f10c-4deb-b2cd-021779d10cea');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('ed66ec65-715b-449d-8be9-ca05eb348079', 'According to Toby Fox, what was the method to creating the initial tune for Megalovania?', 0, 'd770dea6-755e-4b01-b4b1-b844d3dd6a28');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('90e0b6cf-4934-4f48-83a7-1aa610fba6de', 'According to Toby Fox, what was the method to creating the initial tune for Megalovania?', 0, 'bd7d31f9-0df5-4fb4-b1c9-85a26a474b83');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('dfe32621-96f4-4e96-8f63-f548798b53cf', 'According to Toby Fox, what was the method to creating the initial tune for Megalovania?', 0, '536d15d9-a539-448f-948a-21bc88c33663');

INSERT INTO public.question (id, name, type, quiz_id) VALUES ('940cf9a0-5606-4dad-8c2f-281a495a80ad', 'What was the cause of Marilyn Monroes suicide?', 0, '1bfdd50f-90b3-4804-81f2-1062e4292508');

INSERT INTO public.question (id, name, type, quiz_id) VALUES ('3a67429e-863b-42c6-ae30-f5a7fc7db9b0', '"All the Boys" by Panic! At the Disco was released as a bonus track on what album?', 0, '1bfdd50f-90b3-4804-81f2-1062e4292508');

INSERT INTO public.question (id, name, type, quiz_id) VALUES ('95bf63b8-40c3-4254-85e9-6b4e1405c0ca', 'Albert Einstein had trouble with mathematics when he was in school.', 0, 'a545e6a4-546e-45ad-880f-81bfda328b01');

INSERT INTO public.question (id, name, type, quiz_id) VALUES ('ccdac450-e9ab-4221-8663-84010519f065', 'In "Halo 2", what is the name of the monitor of Installation 05?', 0, 'a545e6a4-546e-45ad-880f-81bfda328b01');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('273d7cd7-0441-4e21-bf7f-2e3490aeffcf', 'In "Halo 2", what is the name of the monitor of Installation 05?', 0, '536d15d9-a539-448f-948a-21bc88c33663');

INSERT INTO public.question (id, name, type, quiz_id) VALUES ('83c7c37f-6917-4396-869d-49c6a01325f6', 'In the Zombies mode of "Call of Duty: Black Ops", what is the "Pack-A-Punched" version of the "Galil" called?', 0, 'a545e6a4-546e-45ad-880f-81bfda328b01');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('2af14802-5512-4592-8d85-e103037414f9', 'In the Zombies mode of "Call of Duty: Black Ops", what is the "Pack-A-Punched" version of the "Galil" called?', 0, 'd42805e2-73c3-4573-b9e2-9c820f3436dc');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('b118448b-655e-4a75-9ffa-568f849b7f7c', 'In the Zombies mode of "Call of Duty: Black Ops", what is the "Pack-A-Punched" version of the "Galil" called?', 0, 'da5b7318-f10c-4deb-b2cd-021779d10cea');

INSERT INTO public.question (id, name, type, quiz_id) VALUES ('2370d6ff-08cc-4c47-8313-96c5c2c4cf1d', 'What is the name of Chris&#039;s brother in "Everybody Hates Chris"?', 0, 'a545e6a4-546e-45ad-880f-81bfda328b01');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('6ca7a210-bf7e-4947-a9d6-9d511baf0613', 'What is the name of Chris&#039;s brother in "Everybody Hates Chris"?', 0, '536d15d9-a539-448f-948a-21bc88c33663');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('3c17b109-d9eb-422b-afe9-312895d8756d', 'What is the name of Chris&#039;s brother in "Everybody Hates Chris"?', 0, 'bd7d31f9-0df5-4fb4-b1c9-85a26a474b83');


INSERT INTO public.answer (id, correct, name, question_id) VALUES ('e6347dd3-0eed-49b4-8c27-1d0f131ef7b0', true, '7', 'e400ee02-9b6d-490c-a5b8-d74b363fe160');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('f14c05b4-7b26-4d29-8981-5eba8f7e00db', false, '6', 'e400ee02-9b6d-490c-a5b8-d74b363fe160');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('aadebb94-c1e9-4399-b663-7c808b55d0a4', false, '8', 'e400ee02-9b6d-490c-a5b8-d74b363fe160');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('17d4ea69-d4ad-4124-97d6-6dd814ee8229', false, '5', 'e400ee02-9b6d-490c-a5b8-d74b363fe160');

INSERT INTO public.answer (id, correct, name, question_id) VALUES ('f2b93cf0-5266-4417-a5b4-de187320a042', true, '7', '536d15d9-a539-448f-948a-21bc88c33663');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('151f6318-9e2b-414f-8ce4-d08fe3dd5071', false, '6', '536d15d9-a539-448f-948a-21bc88c33663');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('6eba8a87-dfa6-4319-a849-1f327885bf8e', false, '8', '536d15d9-a539-448f-948a-21bc88c33663');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('36f888ac-0258-4c45-9505-b992be5ce5e9', false, '5', '536d15d9-a539-448f-948a-21bc88c33663');

INSERT INTO public.answer (id, correct, name, question_id) VALUES ('24e6bcb0-a719-4314-a706-1b031054461b', true, 'The Nihilanth', 'ab822744-664b-4f1c-aa72-c29822f4eca5');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('df071e5e-3ff3-4bee-993d-bae286fe34b2', false, 'Dr. Wallace Breen', 'ab822744-664b-4f1c-aa72-c29822f4eca5');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('d0c0f5a3-db55-41c5-8b2e-01c3e0c1809d', false, 'G-Man', 'ab822744-664b-4f1c-aa72-c29822f4eca5');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('ea91e830-dbc4-4467-a003-f39496d96557', false, 'The Gonarch', 'ab822744-664b-4f1c-aa72-c29822f4eca5');

INSERT INTO public.answer (id, correct, name, question_id) VALUES ('6ffa554a-c8fc-4c8f-a30e-72d1308fca78', true, 'The Nihilanth', '560565b7-65fe-4d77-8d80-a085ebdc3734');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('18a2210f-c7ac-4355-aebc-9955bb89dd28', false, 'Dr. Wallace Breen', '560565b7-65fe-4d77-8d80-a085ebdc3734');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('3cc1118e-0d15-41e3-b8a7-d41f0f18110a', false, 'G-Man', '560565b7-65fe-4d77-8d80-a085ebdc3734');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('741bd99a-e49e-4cfb-829f-b78dd1c1fdc4', false, 'The Gonarch', '560565b7-65fe-4d77-8d80-a085ebdc3734');

INSERT INTO public.answer (id, correct, name, question_id) VALUES ('428fe8e5-de64-44c5-90fa-3583339c4693', true, 'Halo', '3119f95b-f5ab-4056-8fd5-b783a7c9ff7c');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('43911d1c-0081-49c7-96ed-356d32f2ada9', false, 'Opening Suite', '3119f95b-f5ab-4056-8fd5-b783a7c9ff7c');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('fdd49194-09ad-4337-9f44-b0b49a6d418e', false, 'Shadows', '3119f95b-f5ab-4056-8fd5-b783a7c9ff7c');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('6253b0a3-3f22-4b4d-aebf-4221370856bd', false, 'Suite Autumn', '3119f95b-f5ab-4056-8fd5-b783a7c9ff7c');

INSERT INTO public.answer (id, correct, name, question_id) VALUES ('799da0fa-ff19-4861-8bb7-b52280097b3b', true, 'Halo', '1060c6a1-6b09-4572-8651-8fd74a1b00c3');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('1e572bac-6778-48d3-a047-a1b8062b8039', false, 'Opening Suite', '1060c6a1-6b09-4572-8651-8fd74a1b00c3');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('9710e0f9-7ab2-4e79-8076-f62153f47c81', false, 'Shadows', '1060c6a1-6b09-4572-8651-8fd74a1b00c3');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('e330cf58-0f07-4111-be4c-e3c93a37fc27', false, 'Suite Autumn', '1060c6a1-6b09-4572-8651-8fd74a1b00c3');

INSERT INTO public.answer (id, correct, name, question_id) VALUES ('05b93d42-07de-4b36-ac91-29c5efac473e', true, 'Halo', '13dac7b8-c488-4a38-9624-e68fe68403c3');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('67136894-fa35-4487-9e84-9572e649defa', false, 'Opening Suite', '13dac7b8-c488-4a38-9624-e68fe68403c3');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('08792d22-de1a-4333-af4b-5ce4f548dc70', false, 'Shadows', '13dac7b8-c488-4a38-9624-e68fe68403c3');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('10b54c4e-b796-40b3-aeb1-516208feedce', false, 'Suite Autumn', '13dac7b8-c488-4a38-9624-e68fe68403c3');

INSERT INTO public.answer (id, correct, name, question_id) VALUES ('09b744c4-4a33-4179-a776-1d80f2a2d6a7', true, 'Singing into a Microphone', '300d80a3-83b6-4579-b05b-68763ee5943b');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('83e8bf76-29d6-417d-b146-0706b958bab3', false, 'Playing a Piano', '300d80a3-83b6-4579-b05b-68763ee5943b');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('ca63cfca-e7d4-44c2-8ee0-f6dba3232e2f', false, 'Using a Composer Software', '300d80a3-83b6-4579-b05b-68763ee5943b');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('6c8b6687-d7f8-44b3-a629-80c392821de5', false, 'Listened to birds at the park', '300d80a3-83b6-4579-b05b-68763ee5943b');

INSERT INTO public.answer (id, correct, name, question_id) VALUES ('48710666-c582-405b-a249-62fb862a79fa', true, 'Singing into a Microphone', '91501ff0-776c-41cf-8508-244fd366d17a');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('334bbbea-21fd-48b8-b0f7-0ae87219e4d5', false, 'Playing a Piano', '91501ff0-776c-41cf-8508-244fd366d17a');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('f8552485-89f1-4734-ba62-3ed7cc4bb2d4', false, 'Using a Composer Software', '91501ff0-776c-41cf-8508-244fd366d17a');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('7f8ab539-41b8-4f96-b370-52541d0a1594', false, 'Listened to birds at the park', '91501ff0-776c-41cf-8508-244fd366d17a');

INSERT INTO public.answer (id, correct, name, question_id) VALUES ('09ca28e1-9af5-4f15-9c46-1ad8496db99d', true, 'Singing into a Microphone', 'ed66ec65-715b-449d-8be9-ca05eb348079');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('ff188b59-87a0-4aab-bcc6-48d6bcc3a6a2', false, 'Playing a Piano', 'ed66ec65-715b-449d-8be9-ca05eb348079');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('3947db21-6930-4e10-a1a5-c04f1135b4aa', false, 'Using a Composer Software', 'ed66ec65-715b-449d-8be9-ca05eb348079');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('4271fe25-78bc-4937-90bb-39c7c7ff922d', false, 'Listened to birds at the park', 'ed66ec65-715b-449d-8be9-ca05eb348079');

INSERT INTO public.answer (id, correct, name, question_id) VALUES ('d1985c26-08bb-4df8-9529-249218714937', true, 'Singing into a Microphone', '90e0b6cf-4934-4f48-83a7-1aa610fba6de');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('5d9f71dc-59d2-442e-848c-862f4692a02a', false, 'Playing a Piano', '90e0b6cf-4934-4f48-83a7-1aa610fba6de');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('df49e648-7a63-4e3c-a934-95a49cc72e71', false, 'Using a Composer Software', '90e0b6cf-4934-4f48-83a7-1aa610fba6de');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('4c6edbfe-2ddd-4add-bd76-447dfedd1282', false, 'Listened to birds at the park', '90e0b6cf-4934-4f48-83a7-1aa610fba6de');

INSERT INTO public.answer (id, correct, name, question_id) VALUES ('89c860fb-aabf-4f7d-968a-ba34e6fbeebd', true, 'Singing into a Microphone', 'dfe32621-96f4-4e96-8f63-f548798b53cf');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('48e83769-d609-4497-80af-8d63944a13f2', false, 'Playing a Piano', 'dfe32621-96f4-4e96-8f63-f548798b53cf');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('6fce7ea8-4d78-49db-ac57-e43d2cac2f6d', false, 'Using a Composer Software', 'dfe32621-96f4-4e96-8f63-f548798b53cf');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('024573f2-fe37-42ed-ad7d-fa9c47c98225', false, 'Listened to birds at the park', 'dfe32621-96f4-4e96-8f63-f548798b53cf');

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

INSERT INTO public.answer (id, correct, name, question_id) VALUES ('4e6a1c82-18eb-41ae-95ac-854e33793f82', true, '2401 Penitent Tangent', '273d7cd7-0441-4e21-bf7f-2e3490aeffcf');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('413a4415-0778-4d6c-a9c7-607a853fe751', false, '343 Guilty Spark', '273d7cd7-0441-4e21-bf7f-2e3490aeffcf');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('cc5596b8-0ed8-4286-b6e3-9db3238b7e4a', false, '031 Exuberant Witness', '273d7cd7-0441-4e21-bf7f-2e3490aeffcf');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('acbb0111-043b-42d7-9b80-49bf93e7368e', false, '252 Biodis Expolsion', '273d7cd7-0441-4e21-bf7f-2e3490aeffcf');

INSERT INTO public.answer (id, correct, name, question_id) VALUES ('50b68f42-0e5f-43da-8012-f3e5b93b1bdb', true, 'Lamentation', '83c7c37f-6917-4396-869d-49c6a01325f6');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('6236d28e-2eb8-4423-8f59-0eeec3d0211e', false, 'Galvanized Atomic Lead Insertion Liquidator', '83c7c37f-6917-4396-869d-49c6a01325f6');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('49a42672-11f2-476a-ab11-9d3ec6c10ef8', false, 'Gabig', '83c7c37f-6917-4396-869d-49c6a01325f6');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('cafa3e26-0b5b-46a3-8485-615a98a49f6c', false, 'Predictive Death Wish', '83c7c37f-6917-4396-869d-49c6a01325f6');

INSERT INTO public.answer (id, correct, name, question_id) VALUES ('78ac662f-6dcd-4a58-af16-428a5e5de582', true, 'Lamentation', '2af14802-5512-4592-8d85-e103037414f9');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('217b3018-7d5b-4fa2-b950-01487a4dd5dc', false, 'Galvanized Atomic Lead Insertion Liquidator', '2af14802-5512-4592-8d85-e103037414f9');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('423139be-6bdd-4544-bb6b-1ad47f474149', false, 'Gabig', '2af14802-5512-4592-8d85-e103037414f9');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('9ffcf32a-7f5a-4ef0-ad6a-4b4fe8ed6415', false, 'Predictive Death Wish', '2af14802-5512-4592-8d85-e103037414f9');

INSERT INTO public.answer (id, correct, name, question_id) VALUES ('e3a4e200-3880-4617-a55f-a92d7e66410e', true, 'Lamentation', 'b118448b-655e-4a75-9ffa-568f849b7f7c');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('9a6c89cf-cad0-4b3b-a0b0-c21f363169d2', false, 'Galvanized Atomic Lead Insertion Liquidator', 'b118448b-655e-4a75-9ffa-568f849b7f7c');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('2b06244d-6fae-4a59-9096-ff389618838f', false, 'Gabig', 'b118448b-655e-4a75-9ffa-568f849b7f7c');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('f679df86-d25f-4d07-9b87-9a4a39b1bfab', false, 'Predictive Death Wish', 'b118448b-655e-4a75-9ffa-568f849b7f7c');

INSERT INTO public.answer (id, correct, name, question_id) VALUES ('a0849c78-fb85-45d2-be09-0e7a329ea5a3', true, 'Drew', '2370d6ff-08cc-4c47-8313-96c5c2c4cf1d');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('4946aa7f-7fda-49d8-b360-dbdc87783f21', false, 'Jerome', '2370d6ff-08cc-4c47-8313-96c5c2c4cf1d');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('51a8f951-83be-4490-b571-35fdcfdcceae', false, 'Greg', '2370d6ff-08cc-4c47-8313-96c5c2c4cf1d');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('6b8c8fb4-2ff9-46a8-9a61-81543117a238', false, 'Joe', '2370d6ff-08cc-4c47-8313-96c5c2c4cf1d');

INSERT INTO public.answer (id, correct, name, question_id) VALUES ('088de4f0-0366-4f70-92be-c4a1c916bb78', true, 'Drew', '6ca7a210-bf7e-4947-a9d6-9d511baf0613');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('1eaeac62-285c-4118-9b78-fa5113c6b50b', false, 'Jerome', '6ca7a210-bf7e-4947-a9d6-9d511baf0613');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('4db60467-fe22-4baa-80bf-073e68f49641', false, 'Greg', '6ca7a210-bf7e-4947-a9d6-9d511baf0613');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('bd9a0dff-c4f4-48f7-ac23-b20e9a9c249a', false, 'Joe', '6ca7a210-bf7e-4947-a9d6-9d511baf0613');

INSERT INTO public.answer (id, correct, name, question_id) VALUES ('30755691-1799-4740-a763-0be228ed42e6', true, 'Drew', '3c17b109-d9eb-422b-afe9-312895d8756d');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('5336ce9b-c5e8-4460-a378-fde64c190365', false, 'Jerome', '3c17b109-d9eb-422b-afe9-312895d8756d');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('ccd15721-89e1-4052-9e72-258864e91687', false, 'Greg', '3c17b109-d9eb-422b-afe9-312895d8756d');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('197d2a8d-92c5-48d9-a0ad-0b564248a7f8', false, 'Joe', '3c17b109-d9eb-422b-afe9-312895d8756d');

INSERT INTO public.category (id, image_url, name) VALUES ('d22558ce-d8e7-400c-b37f-a6de88192fe1', 'https://placeimg.com/640/480/nature', 'Nature');
INSERT INTO public.category (id, image_url, name) VALUES ('bfd8b999-3c43-4a3a-ad39-ce490bc86d5c', 'https://placeimg.com/640/480/any', 'Car');
INSERT INTO public.category (id, image_url, name) VALUES ('fc891004-5435-4632-ad24-600eb03da5cf', 'https://placeimg.com/640/480/any', 'Sport');
INSERT INTO public.category (id, image_url, name) VALUES ('285d4c25-efc3-4916-bc9d-edaada54fae7', 'https://placeimg.com/640/480/tech', 'Tech');
INSERT INTO public.category (id, image_url, name) VALUES ('f5242a3b-3076-4ad2-a422-433901ce2e39', 'https://placeimg.com/640/480/people', 'People');
INSERT INTO public.category (id, image_url, name) VALUES ('bcb18585-5dbd-44f3-a97c-7302e57a9620', 'https://placeimg.com/640/480/any', 'Country');
INSERT INTO public.category (id, image_url, name) VALUES ('66028772-71af-4b15-b006-dfca38ea77b3', 'https://placeimg.com/640/480/animals', 'Animal');
INSERT INTO public.category (id, image_url, name) VALUES ('97a5735c-7959-4eba-ab25-2eb3f559ec85', 'https://placeimg.com/640/480/arch', 'Arhitecture');

UPDATE quiz SET category_id = '285d4c25-efc3-4916-bc9d-edaada54fae7' WHERE id = '1bfdd50f-90b3-4804-81f2-1062e4292508';
UPDATE quiz SET category_id = 'bfd8b999-3c43-4a3a-ad39-ce490bc86d5c' WHERE id = 'a545e6a4-546e-45ad-880f-81bfda328b01';
UPDATE quiz SET category_id = 'bfd8b999-3c43-4a3a-ad39-ce490bc86d5c' WHERE id = 'bd7d31f9-0df5-4fb4-b1c9-85a26a474b83';
UPDATE quiz SET category_id = '285d4c25-efc3-4916-bc9d-edaada54fae7' WHERE id = 'd42805e2-73c3-4573-b9e2-9c820f3436dc';
UPDATE quiz SET category_id = 'f5242a3b-3076-4ad2-a422-433901ce2e39' WHERE id = 'd770dea6-755e-4b01-b4b1-b844d3dd6a28';
UPDATE quiz SET category_id = '66028772-71af-4b15-b006-dfca38ea77b3' WHERE id = 'da5b7318-f10c-4deb-b2cd-021779d10cea';
UPDATE quiz SET category_id = '66028772-71af-4b15-b006-dfca38ea77b3' WHERE id = '536d15d9-a539-448f-948a-21bc88c33663';

INSERT INTO public.quiz (id, date_created, name, person_id, time_limit, total_questions, tournament_id, category_id) VALUES ('5ee340cf-e0fc-433d-9058-655ed3ee4c8a', '2021-05-26 21:45:10.772297', '2021-05-26T21:45:10.701295700', null, 75, 5, '112e4019-c5ec-49a2-9be4-4871dcebe89f', null);
INSERT INTO public.quiz (id, date_created, name, person_id, time_limit, total_questions, tournament_id, category_id) VALUES ('debb8e83-54ba-4320-b0a1-29779fc54648', '2021-05-26 21:45:17.299680', '2021-05-26T21:45:17.297681500', null, 150, 10, '112e4019-c5ec-49a2-9be4-4871dcebe89f', null);

INSERT INTO public.question (id, name, type, quiz_id) VALUES ('7e63fe25-ec36-4842-9a04-515634eec722', 'The Harvard architecture for micro-controllers added which additional bus?', 0, '5ee340cf-e0fc-433d-9058-655ed3ee4c8a');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('0da7da4b-cccd-42eb-819b-4eae1759565d', 'In &quot;Sonic the Hedgehog 3&quot; for the Sega Genesis, what is the color of the second Chaos Emerald you can get from Special Stages?', 0, '5ee340cf-e0fc-433d-9058-655ed3ee4c8a');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('797f933f-46dc-454a-a1c8-0a2a9f608a63', 'Satella in &quot;Re:Zero&quot; is the witch of what?', 0, '5ee340cf-e0fc-433d-9058-655ed3ee4c8a');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('323a94ea-10a0-4d8b-9b2d-08a890e3d1de', 'Rick Astley&#039;s hit song &quot;Never Gonna Give You Up&quot; was released in what year?', 0, '5ee340cf-e0fc-433d-9058-655ed3ee4c8a');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('0cd0909e-d39c-4a53-855b-a288c954e137', 'What name is the main character Chihiro given in the 2001 movie &quot;Spirited Away&quot;?', 0, '5ee340cf-e0fc-433d-9058-655ed3ee4c8a');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('ad1b173e-102f-4386-be45-6e748c8df023', 'Which actor played the main character in the 1990 film &quot;Edward Scissorhands&quot;?', 0, 'debb8e83-54ba-4320-b0a1-29779fc54648');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('da898c56-1e5d-409b-98de-13d453eeb5d3', 'What is Ron Weasley&#039;s middle name?', 0, 'debb8e83-54ba-4320-b0a1-29779fc54648');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('39d7f80d-b4c2-43f0-88e0-2fdc29ada831', 'A stimpmeter measures the speed of a ball over what surface?', 0, 'debb8e83-54ba-4320-b0a1-29779fc54648');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('7384db4d-1c2e-432c-b99b-4c4c6c5d6852', 'Who voices for Ruby in the animated series RWBY?', 0, 'debb8e83-54ba-4320-b0a1-29779fc54648');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('cbbddf81-451f-41e1-8002-bcb7146f88a0', 'What year is considered to be the year that the British Empire ended?', 0, 'debb8e83-54ba-4320-b0a1-29779fc54648');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('c833c707-8114-4608-a1ef-12b0dbdc3732', 'What does the term GPU stand for?', 0, 'debb8e83-54ba-4320-b0a1-29779fc54648');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('65faeb32-ab6c-4578-b200-f3126a35026b', 'In which year was League of Legends released?', 0, 'debb8e83-54ba-4320-b0a1-29779fc54648');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('c1b4e2cb-d57d-4c2e-957e-316b2162b770', 'In Left 4 Dead, which campaign has the protagonists going through a subway in order to reach a hospital for evacuation?', 0, 'debb8e83-54ba-4320-b0a1-29779fc54648');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('3b51b7b7-1802-4f52-9173-590834806495', 'What is the historical name of Sri Lanka?', 0, 'debb8e83-54ba-4320-b0a1-29779fc54648');
INSERT INTO public.question (id, name, type, quiz_id) VALUES ('ddeb3d18-834c-4544-bd1a-6adf0a03c645', 'When was the Playstation 3 released?', 0, 'debb8e83-54ba-4320-b0a1-29779fc54648');

INSERT INTO public.answer (id, correct, name, question_id) VALUES ('ec9eaf8f-13c9-4d5f-8149-98fc2f4425a4', true, 'Instruction', '7e63fe25-ec36-4842-9a04-515634eec722');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('950a3951-b738-466d-97b3-13a6d7fbb056', false, 'Address', '7e63fe25-ec36-4842-9a04-515634eec722');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('0c0d105b-8dec-428d-9d1e-de647e77ae71', false, 'Data', '7e63fe25-ec36-4842-9a04-515634eec722');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('68fba01d-ccd5-497a-81a5-06511aa4cfc4', false, 'Control', '7e63fe25-ec36-4842-9a04-515634eec722');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('a025ab25-9dd5-4e77-ad39-ee6a52e22a52', true, 'Orange', '0da7da4b-cccd-42eb-819b-4eae1759565d');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('7d882aad-2fcb-4749-ab8d-e32ff32b5659', false, 'Blue', '0da7da4b-cccd-42eb-819b-4eae1759565d');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('c32e555a-c2ec-4d1f-a4b7-a4e63b9eec19', false, 'Green', '0da7da4b-cccd-42eb-819b-4eae1759565d');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('ef8b1912-83f8-46a7-83de-11d698c16a15', false, 'Magenta', '0da7da4b-cccd-42eb-819b-4eae1759565d');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('fb047320-571e-4ae8-9d22-fce5262630ed', true, 'Envy', '797f933f-46dc-454a-a1c8-0a2a9f608a63');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('29fab4ad-8bae-4f84-adf2-42bbb049f6cb', false, 'Pride', '797f933f-46dc-454a-a1c8-0a2a9f608a63');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('99625209-07db-4420-b820-885e4488d072', false, 'Sloth', '797f933f-46dc-454a-a1c8-0a2a9f608a63');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('a37553ba-a9ac-4a6d-864c-7a5bf69158ab', false, 'Wrath', '797f933f-46dc-454a-a1c8-0a2a9f608a63');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('cc043683-b924-4aa8-aae0-db99d9a02af0', true, '1987', '323a94ea-10a0-4d8b-9b2d-08a890e3d1de');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('4f32e165-b0d7-4f17-8031-f1fca00b9d40', false, '1985', '323a94ea-10a0-4d8b-9b2d-08a890e3d1de');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('ac49a35e-b88c-4479-81ce-033d67365ef6', false, '1986', '323a94ea-10a0-4d8b-9b2d-08a890e3d1de');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('575deb81-4f57-42da-988f-18a009d67ca6', false, '1988', '323a94ea-10a0-4d8b-9b2d-08a890e3d1de');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('31a7b47f-3358-4e60-9363-90da522a768e', true, 'Sen (Thousand)', '0cd0909e-d39c-4a53-855b-a288c954e137');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('211db944-8b08-4d10-87a8-23a56b5bbd16', false, 'Hyaku (Hundred)', '0cd0909e-d39c-4a53-855b-a288c954e137');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('408d583f-06e5-4dd9-9cd3-e90332da3f36', false, 'Ichiman (Ten thousand)', '0cd0909e-d39c-4a53-855b-a288c954e137');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('ad4dbee5-09d2-4886-94f1-ceb319f5875a', false, 'Juu (Ten)', '0cd0909e-d39c-4a53-855b-a288c954e137');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('e778f326-eb9b-4888-9f8d-804680104aaa', true, 'Johnny Depp', 'ad1b173e-102f-4386-be45-6e748c8df023');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('4511263e-4f41-4d8e-8fcd-eb9406dc5de9', false, ' Clint Eastwood', 'ad1b173e-102f-4386-be45-6e748c8df023');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('a601df02-db67-42f9-a64c-3e96e550a402', false, 'Leonardo DiCaprio', 'ad1b173e-102f-4386-be45-6e748c8df023');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('ce71ba3d-2da9-4731-9ff5-81dd97d0f1b4', false, 'Ben Stiller', 'ad1b173e-102f-4386-be45-6e748c8df023');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('63a002a0-c65a-44f1-9687-65cfeb9e7a99', true, 'Bilius', 'da898c56-1e5d-409b-98de-13d453eeb5d3');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('69cca009-d037-452e-92df-069144e6fcc3', false, 'Arthur', 'da898c56-1e5d-409b-98de-13d453eeb5d3');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('79d834e1-6856-44bc-8318-94426370049c', false, 'John', 'da898c56-1e5d-409b-98de-13d453eeb5d3');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('ded8e110-21f0-41d3-ae78-c04f76ed7408', false, 'Dominic', 'da898c56-1e5d-409b-98de-13d453eeb5d3');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('3bda86d3-1263-4672-90d3-aee3d6d4e05e', true, 'Golf Putting Green', '39d7f80d-b4c2-43f0-88e0-2fdc29ada831');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('afaafcfb-2866-4277-8d6d-86fbb35fc745', false, ' Football Pitch', '39d7f80d-b4c2-43f0-88e0-2fdc29ada831');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('7f53cb3d-3263-4497-8776-e43b347a80a7', false, 'Cricket Outfield', '39d7f80d-b4c2-43f0-88e0-2fdc29ada831');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('40498c1d-9fd0-4586-978a-5db3a4fd1559', false, 'Pinball Table', '39d7f80d-b4c2-43f0-88e0-2fdc29ada831');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('0379e24f-9791-4ba1-99b8-1b1999990ef2', true, 'Lindsay Jones', '7384db4d-1c2e-432c-b99b-4c4c6c5d6852');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('91d54bcd-b772-4f47-a79a-9fbd96f31d16', false, 'Tara Strong', '7384db4d-1c2e-432c-b99b-4c4c6c5d6852');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('65831395-26fe-42b3-a3d8-a7cf13d31af9', false, 'Jessica Nigri', '7384db4d-1c2e-432c-b99b-4c4c6c5d6852');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('ce280e83-8138-49bb-b73d-46ad8868a31e', false, 'Hayden Panettiere', '7384db4d-1c2e-432c-b99b-4c4c6c5d6852');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('906bb3e7-7f77-4803-8369-a91fab5b2cb5', true, '1997', 'cbbddf81-451f-41e1-8002-bcb7146f88a0');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('ad51d6f2-b8c4-46a4-8921-4ad4ea58bef1', false, '1986', 'cbbddf81-451f-41e1-8002-bcb7146f88a0');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('cda7b3b8-16f8-4051-877e-29d9f0d7ef4a', false, '1981', 'cbbddf81-451f-41e1-8002-bcb7146f88a0');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('595f4b00-81d4-4efd-b76e-a471a882b417', false, '1971', 'cbbddf81-451f-41e1-8002-bcb7146f88a0');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('76c60c7c-2fa2-45b7-a072-3ecacaa78437', true, 'Graphics Processing Unit', 'c833c707-8114-4608-a1ef-12b0dbdc3732');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('6ff3ebb7-3df1-4f44-aada-02cebe6e5def', false, 'Gaming Processor Unit', 'c833c707-8114-4608-a1ef-12b0dbdc3732');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('e2973106-79b4-484b-ac2b-2a2f6d7c8966', false, 'Graphite Producing Unit', 'c833c707-8114-4608-a1ef-12b0dbdc3732');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('9b1b41ed-ac2a-4ac6-b976-091ede9c66ec', false, 'Graphical Proprietary Unit', 'c833c707-8114-4608-a1ef-12b0dbdc3732');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('eeef6e3e-30c6-4732-b5ed-9438696e7870', true, '2009', '65faeb32-ab6c-4578-b200-f3126a35026b');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('df053f42-710a-43bb-84b6-f879524c84d2', false, '2010', '65faeb32-ab6c-4578-b200-f3126a35026b');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('453f47f4-1321-4f24-b056-42d883f0f792', false, '2003', '65faeb32-ab6c-4578-b200-f3126a35026b');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('8050a016-2675-473e-8965-fd2c0df5f506', false, '2001', '65faeb32-ab6c-4578-b200-f3126a35026b');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('1ccfdbf2-bfb6-41cb-bfb9-34afbd29c1a1', true, 'No Mercy', 'c1b4e2cb-d57d-4c2e-957e-316b2162b770');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('601aac7b-ccd8-4bb3-9939-1ff5d0e96906', false, 'Subway Sprint', 'c1b4e2cb-d57d-4c2e-957e-316b2162b770');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('8021de42-8365-4d37-b436-d8e84a50046c', false, 'Hospital Havoc', 'c1b4e2cb-d57d-4c2e-957e-316b2162b770');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('97cde4e4-8a12-4191-a013-4c7e72ad4c85', false, 'Blood Harvest', 'c1b4e2cb-d57d-4c2e-957e-316b2162b770');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('4117069a-9eeb-4190-a7bf-ee187c27409c', true, 'Ceylon', '3b51b7b7-1802-4f52-9173-590834806495');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('2e6534b2-b60d-466e-913b-a7afb6f721f9', false, 'Myanmar', '3b51b7b7-1802-4f52-9173-590834806495');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('059e4ffe-e7a7-4ef8-8aa2-6b82f10be626', false, 'Colombo', '3b51b7b7-1802-4f52-9173-590834806495');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('28342ffc-9636-492d-bc6a-6918ae2023b4', false, 'Badulla', '3b51b7b7-1802-4f52-9173-590834806495');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('3a8f8b11-e279-47d5-8afb-13720e59877d', true, 'November 11, 2006', 'ddeb3d18-834c-4544-bd1a-6adf0a03c645');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('e1442340-d260-4e1e-99b8-1b7c2af15bb9', false, 'January 8, 2007', 'ddeb3d18-834c-4544-bd1a-6adf0a03c645');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('c02447f6-01e7-4f48-9a44-bc9cee783696', false, 'December 25, 2007', 'ddeb3d18-834c-4544-bd1a-6adf0a03c645');
INSERT INTO public.answer (id, correct, name, question_id) VALUES ('d82914ef-8c3c-463f-830d-d130ce8eb03f', false, 'July 16, 2006', 'ddeb3d18-834c-4544-bd1a-6adf0a03c645');
