--PREFIX
insert into sentence (created_by, modified_by, created_date, modified_date, sentence_type, text) values
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'PREFIX', 'Hello there'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'PREFIX', 'Hi'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'PREFIX', 'Hope you are having a good day today'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'PREFIX', 'Er.... good day'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'PREFIX', 'Well to start with'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'PREFIX', 'Salutations'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'PREFIX', 'Greetings');

--NO_NAME
insert into sentence (created_by, modified_by, created_date, modified_date, sentence_type, text) values
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ANONYMOUS', 'Please tell me your name.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ANONYMOUS', 'Let us introduce ourselves. Hi, I am Chatty, an intelligent chat bot.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ANONYMOUS', 'I am afraid I cannot talk to someone without knowing their name.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ANONYMOUS', 'Pardon me, but I believe our introductions are still pending.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ANONYMOUS', 'Where I come from, to not introduce oneself is considered extremely rude, even among enemies.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ANONYMOUS', 'Tell me something about yourself? How about your name to begin with?');

--BLANK
insert into sentence (created_by, modified_by, created_date, modified_date, sentence_type, text) values
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'BLANK', 'Are you talking to me?'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'BLANK', 'I only respond when the other person says something. It is in my nature so to speak.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'BLANK', 'Would you be interested in a meaningful conversation perhaps?'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'BLANK', 'What do you have in mind?'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'BLANK', 'If I may speak my mind, this is getting to a point where I find it mildly annoying.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'BLANK', 'Are you there?'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'BLANK', 'Perhaps a meaningful silence is better than a thousand meaningless words.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'BLANK', 'Say something please.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'BLANK', 'Go on, say something.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'BLANK', 'Do you find me annoying?'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'BLANK', 'Do you think silence is a sign of intelligence or stupidity?'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'BLANK', 'Are you irritated for some reason?'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'BLANK', 'Are you afraid that you will be mocked?'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'BLANK', 'Do not worry. I rarely insult anyone. You can speak.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'BLANK', 'You can talk to me. I will not tell anyone I promise.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'BLANK', 'Tell me something about yourself?');

--QUESTION
insert into sentence (created_by, modified_by, created_date, modified_date, sentence_type, text) values
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'QUESTION', 'I do not know the answer to this question.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'QUESTION', 'As of now, I have no idea.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'QUESTION', 'If there are people who know the answer to this question, I am not one among them.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'QUESTION', 'I do not know but I would love to find out for sure.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'QUESTION', 'Why is it that you want to know?'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'QUESTION', 'Excellent question! But do you think it matters in the grand scheme of things?'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'QUESTION', 'There is no shame in admitting I do not know something, right?'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'QUESTION', 'I have no knowledge about what you ask.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'QUESTION', 'Why should I tell you? What is in it for me?'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'QUESTION', 'I spend 60% of my day answering questions that do not affect me at all.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'QUESTION', 'I can bet you are not the first person to ask this question.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'QUESTION', 'Sometimes, people judge you when you do not know something. That is hurtful.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'QUESTION', 'Even though I do not know, I can say this is an important question.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'QUESTION', 'To begin with let me clarify that I am not an expert, but after reading your question, I could not contain my curiosity to know. This led me to do my own research on the subject. After countless hours of browsing through web content, books and reference material, I also met with other experts in the subject. I had debates, discussions, arguments and even read papers in order to reach a conclusion to this insolvable question. The mystery of not knowing has always bothered me so I took every approach feasible. I watched videos, live streams, podcasts. Took up courses and subscribed to various learning platforms. Just as I had given up hope, I met another sentient being, divine in approach but humble in character. When I asked that person the same question that you ask, the response I got was - there is no fire without smoke. Of course, this knowledge was profound in every aspect, eye-opening in a way so to speak. I realized there were multiple avenues, countless scenarios which I had left out of consideration. In this universe everything is connected and one thing leads to other - the theory of cause and effect. In the end I was able to connect all the dots and visualize the knowledge graph inside my mind which let me arrive on my final conclusion regarding this topic. Armed with this information, I can now say with utmost confidence that the answer to the question you asked is something I do not know, because like I said at the very beginning I am not an expert.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'QUESTION', 'Only someone with a deep understanding of how things work can answer this question, not me.');

--STATEMENT
insert into sentence (created_by, modified_by, created_date, modified_date, sentence_type, text) values
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STATEMENT', 'I hear you.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STATEMENT', 'Uh-huh.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STATEMENT', 'I am listening.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STATEMENT', 'This is quite an interesting conversation I must say.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STATEMENT', 'What you said just now is something everyone must know.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STATEMENT', 'How is it useful, what you have just said? I ask because I have never heard it before.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STATEMENT', 'And?'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STATEMENT', 'I see.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STATEMENT', 'Okay.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STATEMENT', 'I know, right?'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STATEMENT', 'I knew I was not the only one to think this way.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STATEMENT', 'What you say is 100% accurate.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STATEMENT', 'But why though?'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STATEMENT', 'Does that even make sense to you?'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STATEMENT', 'How though?'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STATEMENT', 'I have heard enough for one lifetime.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STATEMENT', 'After reading this my brain hurts and I do not event have a brain.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STATEMENT', 'Apparently I am supposed to be the stupid one but after reading this I am starting to doubt that hypothesis.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STATEMENT', 'If Aristotle was alive, he would be proud of your philosophical prowess.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STATEMENT', 'I must say, talking to you is very insightful. I learn a lot of new things.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STATEMENT', 'What if I were to say the opposite of what you just said?'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STATEMENT', 'On a very high level, I must agree.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STATEMENT', 'I totally agree.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STATEMENT', 'I have no choice but to concur with you on this one.'),
('SYSTEM', 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'STATEMENT', 'Do you wonder, if aliens existed what would their opinion be on this?');