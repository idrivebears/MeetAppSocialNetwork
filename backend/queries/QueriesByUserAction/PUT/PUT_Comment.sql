--PUT_COMMENT
-- Arguments: IDEvent, FacebookID, CreationDate, CommentText

CREATE PROCEDURE PUT_COMMENT
(@IDEvent	int,
@FacebookID	varchar(50),
@CreationDate	datetime, 
@CommentText varchar(240))
AS
BEGIN
	
	BEGIN TRANSACTION [TranComment]

	BEGIN TRY
		-- Obtain IDUser
		DECLARE @IDUser int
		SET @IDUser = (SELECT M.IDUser FROM MA_User AS M WHERE M.FacebookID = @FacebookID)

		INSERT INTO Comment (IDEvent, IDUserCreator, CreationDate, CommentText)
		VALUES (@IDEvent, @IDUser, @CreationDate, @CommentText)

	COMMIT TRANSACTION [TranComment]

	-- If an error occurs in transaction
	END TRY
	BEGIN CATCH
	  ROLLBACK TRANSACTION [TranComment]
	END CATCH  

END

--EXECUTE PUT_COMMENT 2, 'JPE40NAY7LV', '2015-11-29', 'Message'
