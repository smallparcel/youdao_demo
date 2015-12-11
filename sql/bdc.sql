DROP DATABASE IF EXISTS bcd;
CREATE DATABASE bcd;

DROP TABLE IF EXISTS bdc.word;
CREATE TABLE bcd.word(
  id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY ,
  english VARCHAR(255),
  phonetic_uk VARCHAR(255) COMMENT 'Ӣʽ����',
  phonetic_us VARCHAR(255) COMMENT '��ʽ����',
  audio_uk_male VARCHAR(255) COMMENT 'Ӣʽ���������',
  audio_uk_female VARCHAR(255) COMMENT 'ӢʽŮ�������',
  audio_us_male VARCHAR(255),
  audio_us_female VARCHAR(255)
);

DROP TABLE IF EXISTS bcd.word_definition;
CREATE TABLE bcd.word_definition(
  id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY ,
  part_of_speech VARCHAR(255) COMMENT '����',
  chinese VARCHAR(255) COMMENT '����',
  word_id INT(11) UNSIGNED
)COMMENT '���Ա�';

DROP TABLE IF EXISTS bcd.word_sentence;
CREATE TABLE bcd.word_sentence(
  id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY ,
  english VARCHAR(255) COMMENT 'Ӣ������',
  chinese VARCHAR(255) COMMENT '���ķ���',
  source VARCHAR(255),
  audio_male VARCHAR(255),
  audio_female VARCHAR(255),
  word_id INT(11) UNSIGNED
)COMMENT '�����';

DROP TABLE IF EXISTS bcd.morpheme;
CREATE TABLE bcd.morpheme(
  id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  english VARCHAR(255),
  type VARCHAR(255) COMMENT '���ͣ�root,prefix,infix,suffix'
)COMMENT '���ر�';

DROP TABLE IF EXISTS bcd.morpheme_definition;
CREATE TABLE bcd.morpheme_definition(
  id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  part_of_speech VARCHAR(255) COMMENT '����',
  chinese VARCHAR(255),
  morpheme_id INT(11) UNSIGNED
)COMMENT '���ض����';

DROP TABLE IF EXISTS bcd.word_morpheme;
CREATE TABLE bcd.word_morpheme(
  id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY ,
  word_id INT(11) UNSIGNED,
  morpheme_id INT(11) UNSIGNED
) COMMENT '���ʴ��ع�����';

ALTER TABLE bcd.word_definition ADD CONSTRAINT fk_word_definition_word_id FOREIGN KEY (word_id)REFERENCES bcd.word(id);
ALTER TABLE bcd.word_sentence ADD CONSTRAINT fk_word_sentence_word_id FOREIGN KEY (word_id)REFERENCES bcd.word(id);
ALTER TABLE bcd.word_morpheme ADD CONSTRAINT fk_word_morpheme_word_id FOREIGN KEY (word_id)REFERENCES bcd.word(id);
ALTER TABLE bcd.word_morpheme ADD CONSTRAINT fk_word_morpheme_morpheme_id FOREIGN KEY (morpheme_id)REFERENCES bcd.morpheme(id);
ALTER TABLE bcd.morpheme_definition ADD CONSTRAINT fk_morpheme_definition_morpheme_id FOREIGN KEY (morpheme_id) REFERENCES bcd.morpheme(id);
