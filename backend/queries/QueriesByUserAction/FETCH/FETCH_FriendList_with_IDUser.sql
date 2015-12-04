-- FETCH list of friends given IDUser
-- auxiliar procedure

CREATE PROCEDURE FETCH_FRIEND_LIST_WITH_ROWID
(@IDUser int)
AS
BEGIN
	
	SELECT 
			ROW_NUMBER() OVER (ORDER BY USER2.IDUser) as RowId,
			USER2.IDUser
	FROM	
			MA_User AS USER1,
			MA_User AS USER2,
			MA_Users_Relation
	WHERE	
			USER1.IDUser = MA_Users_Relation.IDUser_1
		AND	USER2.IDUser = MA_Users_Relation.IDUser_2
		AND	USER1.IDUser = @IDUser
	ORDER BY
			RowId
END


--EXECUTE FETCH_FRIEND_LIST_WITH_ROWID 1