#
# Tabellenstruktur f�r Tabelle `quizcontent`
# TabellenTyp kann auch ausgetauscht werden!

CREATE TABLE quizcontent (
	Frage varchar(255) NOT NULL default '',
	RichtigeAntwort varchar(255) NOT NULL default '',
	Antwort2 varchar(255) NOT NULL default '',
	Antwort3 varchar(255) default NULL,
	Antwort4 varchar(255) default NULL,
	Vorkommentar text NOT NULL,
	Nachkommentar text NOT NULL,
	BildURL varchar(255) NOT NULL default '',
	Thema varchar(255) NOT NULL default '',
	S_Stufe int(11) NOT NULL default '3',
	Sprache varchar(255) NOT NULL default 'german'
) TYPE=InnoDB;




INSERT INTO quizcontent VALUES ('Wie wird das windstille Zentrum eines Wirbelsturms genannt?','Auge','Herz','Hirn','K�rper','','','','Erdkunde',3,'german');
INSERT INTO quizcontent VALUES ('Wie nennt man die starken Regenf�lle, die zu bestimmten Jahreszeiten in Asien niedergehen?','Monsun','Taikun','Orkan','Zyklon','','','','Erdkunde',1,'german');
INSERT INTO quizcontent VALUES ('Was ist ein Mittelgebirge?','ein Gebirge von mittlerer H�he','die Mitte eines Gebirges','ein Gebirge in der Mitte eines Landes','ein Gebirge zwischen zwei Gebirgen','','','','Erdkunde',3,'german');
INSERT INTO quizcontent VALUES ('Welche K�stenform gibt es nicht?','Geestk�ste','Sch�renk�ste','Boddenk�ste','F�rdenk�ste','','Die Geest ist eine flachwellige Landschaft im nordwestdeutschen Tiefland. Sie liegt hinter dem Marschland, ist somit keinesfalls eine K�stenform. Encarta-Enzyklop�die','','Erdkunde',3,'german');
INSERT INTO quizcontent VALUES ('Dass die Indianer und die Tschuktschen in Sibirien gleicher Abstammung sind, ahnt man schon. Wie aber kamen die ersten "Indianer" nach Nordamerika?','W�hrend des Pleistoz�n bestand eine Landbr�cke, weil der Meeresspiegel ca. 100 m tiefer lag als heute.','Mit Beginn des Holoz�n begannen sich die Erdmassen zu trennen. In dieser Zeit wechselten die Menschen Richtung heutigem Nordamerika.','Nach der letzten Eiszeit setzten die ersten Menschen vor ca. 9.000 Jahren zum heutigen Nordamerika �ber.','Es fand nie ein Wechsel Richtung Nordamerika, sondern Richtung Sibirien statt und dieser Wechsel vollzog sich vor ca. 6.500 Jahren per Kanu (engste Meerstelle: ca. 60km).','','Im Pleistoz�n (Eiszeit) wurde ein Teil des Meerwassers als Eis gebunden, dadurch sank der Meeresspiegel um etwa 100 Meter ab. So entstand die Landbr�cke �ber die Beringstra�e, die die Besiedlung Amerikas durch den Menschen von Asien aus erm�glichte. <br>Encarta-Enzyklop�die','','Erdkunde',5,'german');
INSERT INTO quizcontent VALUES ('Welcher der folgenden Begriffe bezeichnet keinen Wind?','Tsunami','Bora','Mistral','Schirokko','','www.wissen.de','','Erdkunde',3,'german');
INSERT INTO quizcontent VALUES ('Wie nennt man die Wolken, die sehr hoch am Himmel d�nne wei�e Streifen bilden und schlechtes Wetter ank�ndigen?','Federwolken','Haufenwolken','Sch�fchenwolken','Schichtwolken','','http://www.bauernregeln.net/40.html','','Erdkunde',3,'german');
INSERT INTO quizcontent VALUES ('In welcher Zeitzone liegt Saudi-Arabien?','MEZ + 2 Stunden','MEZ + 3 Stunden','GMT + 1 Stunde','GMT + 4 Stunden','','','','Erdkunde',5,'german');
INSERT INTO quizcontent VALUES ('Wer schuf die ersten modernen Landkarten?','Mercator','Beheim','Mercalli','Marconi','','','','Erdkunde',4,'german');
INSERT INTO quizcontent VALUES ('Breite Flussm�ndungen mit vielen Flussarmen nennt man ...?','Delta','Alpha','Beta','Gamma','','','','Erdkunde',1,'german');
INSERT INTO quizcontent VALUES ('Wer war w�hrend des zweiten Weltkrieges US-Pr�sident?','Franklin D. Roosevelt','Richard Nixon','John F. Kennedy','Ronald Reagan','','','','Geschichte',1,'german');
INSERT INTO quizcontent VALUES ('Wie hie� die deutsche Verteidigungslinie in Italien, auf der im zweiten Weltkrieg das Kloster Monte Carlo lag?','Gustavlinie','Ferdinandlinie','Franzlinie','Gretellinie','','','','Geschichte',2,'german');
INSERT INTO quizcontent VALUES ('Wem gelang es, im 1.Weltkrieg Deutsch-Ostafrika als einzige deutsche Kolonie bis zum Ende des Krieges gegen die britischen, belgischen und portugiesischen Truppen zu verteidigen?','Paul von Lettow-Vorbeck','Lothar von Trotha','Freiherr von Schleinitz','Graf von G�tzen','','http://de.wikipedia.org/wiki/Paul_von_Lettow-Vorbeck','','Geschichte',1,'german');
INSERT INTO quizcontent VALUES ('1994 fand in Ruanda einer der schlimmsten V�lkermorde der Menschheitsgeschichte statt als Ergebnis einer jahrzehntelangen Feindschaft zwischen zwei Volksgruppen. Wie hei�en diese?','Hutu und Tutsi','Zulu und Xhosa','Bemda und Nyanja','Kikuyu und Massai','','http://de.wikipedia.org/wiki/Ruanda<br>Bev�lkerung: Mehrheit Hutu (85%) , Minderheit Tutsi (14%).','','Geschichte',2,'german');
INSERT INTO quizcontent VALUES ('Welches Giftgas wurde w�hrend des 2. Weltkrieges durch die Alliierten gegen St�dte in Japan eingesetzt?','Napalm','Agent Orange','Sulphurdioxid','Senfgas','','http://de.wikipedia.org/wiki/Napalm<br>Napalm wurde w�hrend des 2. Weltkrieges durch die Alliierten gegen St�dte in Japan und sp�ter durch die USA im Vietnamkrieg eingesetzt.','','Geschichte',2,'german');
INSERT INTO quizcontent VALUES ('Welche deutsche Stadt wurde im zweiten Weltkrieg als Erste von den Amerikanern eingenommen?','Aachen','Kaiserslautern','Mannheim','Koblenz','','','','Geschichte',2,'german');
INSERT INTO quizcontent VALUES ('Byzanz und Konstantinopel sind alle Namen f�r?','Istanbul','Bagdad','Damaskus','Athen','','','','Geschichte',1,'german');
INSERT INTO quizcontent VALUES ('Welche ber�hmte Person aus der Antike lie� sich von einer Schlange bei�en, um Selbstmord zu begehen?','Cleopatra','C�sar','Marc Anton','Sophokles','','','','Geschichte',2,'german');
INSERT INTO quizcontent VALUES ('In welchem Jahr gr�ndeten Uri, Schwyz und Unterwalden die Eidgenossenschaft?','1291','1266','1298','1307','','','','Geschichte',1,'german');
INSERT INTO quizcontent VALUES ('Welche Stadt wurde nach Ende des 2.Weltkrieges von "Rosinenbombern" versorgt?','Westberlin','Frankfurt','M�nchen','Hamburg','','','','Geschichte',2,'german');
INSERT INTO quizcontent VALUES ('Wie viele Punkte ergibt der erste gewonnene Ballwechsel beim Tennis?','15','1','5','10','','','','Sport',2,'german');
INSERT INTO quizcontent VALUES ('In welcher Sportart kann man einen Satz mit 25:21 gewinnen?','Volleyball','Tischtennis','Badminton','Prellball','','','','Sport',5,'german');
INSERT INTO quizcontent VALUES ('Welche alte ostasiatische Kampfsportart perfektionierte Bruce Lee?','Kung Fu','Makramee','Ikebana','Manga','','','','Sport',1,'german');
INSERT INTO quizcontent VALUES ('In welcher Sportart m�ssen die Tore laut Regeln 7,32 Meter breit sein?','Fu�ball','Handball','Wasserball','Feldhockey','','','','Sport',1,'german');
INSERT INTO quizcontent VALUES ('Welche Stadt in Baden-W�rttemberg ist vor allem bei Leichtathletik-Freunden bekannt?','Kornwestheim','Biers�dheim','Weinostheim','Sektnordheim','','','','Sport',3,'german');
INSERT INTO quizcontent VALUES ('Was machen die Sportler beim Triathlon nicht?','reiten','schwimmen','Rad fahren','laufen','','','','Sport',1,'german');
INSERT INTO quizcontent VALUES ('Welche Sportart betreibt Hilde Gerg?','Skifahren','Radsport','Schwimmen','Tennis','','','','Sport',1,'german');
INSERT INTO quizcontent VALUES ('Wie lang dauert ein regul�res Handball - Spiel?','2 x 30 Minuten','2 x 45 Minuten','2 x 20 Minuten','2 x 35 Minuten','','','','Sport',3,'german');
INSERT INTO quizcontent VALUES ('Was f�r eine Sportart wird in dem Wappen von SpVgg Unterhaching au�er Fu�ball noch dargestellt?','Bobsport','Bogenschie�en','Handball','Skispringen','','','','Sport',4,'german');
INSERT INTO quizcontent VALUES ('Welcher deutsche Leichtathlet war 2000 in einen Dopingskandal verwickelt?','Dieter Baumann','Frank Busemann','Heinz Wei�','Lars Riedel','','','','Sport',2,'german');
INSERT INTO quizcontent VALUES ('Welche Formel passt: Die Energie, die einem K�rper innewohnt, entspricht seiner Masse, multipliziert mit dem Quadrat der Lichtgeschwindigkeit?','E = mc�','E = mg�','A= A+B-B','E = mc�','','','','Nat.-wissenschaften',2,'german');
INSERT INTO quizcontent VALUES ('Auf welchem Prinzip basiert die chinesische Astrologie?','auf dem Sonne-Mond Zyklus','auf keinem','auf den Mondphasen','auf dem Jahresablauf','','','','Nat.-wissenschaften',3,'german');
INSERT INTO quizcontent VALUES ('Wann wurde der Nobelpreis erstmals an einen Deutschen verliehen?','1901','1908','1912','1883','','Physik: Wilhelm Conrad Roentgen f�r die Entdeckung der nach ihm benannten Strahlen<br>Medizin: Emil von Behring f�r die Entdeckung eines Wirkstoffs gegen Diphterie und seine Arbeiten �ber Serumtherapie','','Nat.-wissenschaften',5,'german');
INSERT INTO quizcontent VALUES ('Wo findet man das "Keilbein"?','am Sch�del','am Oberschenkelknochen','am Brustbein','an der Handwurzel','','Os sphenoidale befindet sich ungef�hr in Schl�fenh�he am Sch�del.','','Nat.-wissenschaften',5,'german');
INSERT INTO quizcontent VALUES ('Welches dieser Lebewesen ist das stammesgeschichtlich �lteste?','Blaualge','Rotalge','Gr�nalge','Kieselalge','','Blaualgen oder Cyanophyceen sind keine Algen im eigentlichen Sinn, sondern werden zu den Bakterien gerechnet.','','Nat.-wissenschaften',5,'german');
INSERT INTO quizcontent VALUES ('Woraus wird Lanolin gewonnen?','Wolle','Walfett','Robben�l','Katzendarm','','Der erste Schritt bei der Verarbeitung von Wolle zu Stoffen ist das Sortieren der Fasern nach Feinheit, L�nge und Gleichm��igkeit. Dann werden die Fasern vom Wollschwei� gereinigt, der aus Lanolin und getrocknetem Schwei� besteht, dem so genannten Wollfett.','','Nat.-wissenschaften',4,'german');
INSERT INTO quizcontent VALUES ('Warum ist die Banane krumm?','wegen des Geotropismus','wegen der Anamorphose','wegen des Bioflorismus','wegen der Parazentese','','','','Nat.-wissenschaften',5,'german');
INSERT INTO quizcontent VALUES ('Nach wem wurde die erste st�ndig besetzte Antarktisstation der Bundesrepublik Deutschland benannt?','von Neumayer','von Humboldt','Senckenberg','Amundsen','','Georg Balthasar von Neumayer(1826-1909), deutscher Hydrograph und Geophysiker. Von 1879 an Vorsitzender der Internationalen Polarkommission.','','Nat.-wissenschaften',5,'german');
INSERT INTO quizcontent VALUES ('In welchem dieser Erdzeitalter lebten die Trilobiten?','Silur','Devon','Karbon','Perm','','http://www.fossa.de/Trilobita/trilobita.html','','Nat.-wissenschaften',5,'german');
INSERT INTO quizcontent VALUES ('Was ist ein britisches Stone?','ca. 7 Kilogramm','ca. 4 Liter','ca. 6 Meter','ca. 1 Stunde','','','','Nat.-wissenschaften',3,'german');
