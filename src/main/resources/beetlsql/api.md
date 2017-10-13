one
===
SELECT
	* 
FROM
	blade_notice 
WHERE
	id = #{id}

list
===
SELECT
	* 
FROM
	blade_notice 
WHERE
	1 = 1 
@ if (isNotEmpty(title)) {
    AND title LIKE concat('%',#{title},'%')
@ }

page
===
SELECT
@ pageTag(){
    *
@ } 
FROM
	blade_notice 
WHERE
	1 = 1 
@ if (isNotEmpty(title)) {
    AND title LIKE concat('%',#{title},'%')
@ }
@ pageIgnoreTag() {
    ORDER BY ID DESC
@ }