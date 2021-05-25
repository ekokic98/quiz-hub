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

INSERT INTO public.quiz (id, date_created, name, person_id, time_limit, total_questions, tournament_id, category_id) VALUES ('debb8e83-54ba-4320-b0a1-29779fc54648', '2021-04-07 22:17:14.059577', 'Novie kviz (tournament)', null, 25, 13, '112e4019-c5ec-49a2-9be4-4871dcebe89f', '66028772-71af-4b15-b006-dfca38ea77b3');
INSERT INTO public.quiz (id, date_created, name, person_id, time_limit, total_questions, tournament_id, category_id) VALUES ('f1e252f0-737e-4fb0-87a8-2cd23a18b4f9', '2021-04-07 22:17:14.059577', 'Kviz (tournament)', null, 30, 23, '112e4019-c5ec-49a2-9be4-4871dcebe89f', 'bcb18585-5dbd-44f3-a97c-7302e57a9620');
