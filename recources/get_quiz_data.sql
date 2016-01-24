SELECT quiz_data._id,
	quiz_data.question,
	quiz_data.correct_answer,
	quiz_data.answer_b,
	quiz_data.answer_c,
	quiz_data.answer_d,
	quiz_data.difficulty,
	quiz_categories.categoryname,
	quiz_localization.languagecode 
FROM quiz_data, quiz_categories, quiz_localization 
WHERE quiz_data.category=quiz_categories._id 
AND quiz_data.locale=quiz_localization._id
AND quiz_categories.categoryname='category_history'