delete from quiz_taker;
delete from question;

alter sequence quiz_taker_id_seq restart with 1;
alter sequence question_id_seq restart with 1;

insert into quiz_taker(username, score, date_registered)
values('Joe', 40, '2022-05-14'),
('Jack', 80, '2022-08-02'),
('Eileen', 60, '2023-02-23');

insert into question(question_text, option_0, option_1,
option_2, option_3, correct_option_index)
values('2 + 2 = ?', '0', '1', '4', '3', 2),
('What is Java?', 'A car', 'A programming language',
'A phone', 'A star', 1),
('What is the star in our solar system called?',
'Sirius', 'VV Cephei', 'Sun', 'Rigel', 2),
('Who is the founder of The Republic of Türkiye?',
'Mustafa Kemal Atatürk', 'Joe Biden', 'Enver Paşa',
'Fevzi Çakmak', 0),
('In which mythology does the underground god Erlik belong?',
'Scandinavian', 'Greek', 'Aztec', 'Turkic', 3);