--PUT_EVENT_ATTENDANCE
-- Arguments: IDEvent, FacebookID, Attendance = 0 |	1

CREATE PROCEDURE PUT_EVENT_ATTENDANCE
(@IDEvent int,
@FacebookID	varchar(50),
@Attendance bit)
AS
BEGIN

	BEGIN TRANSACTION [TranAttend]

	BEGIN TRY
		
		-- Obtain IDUser
		DECLARE @IDUser int
		SET @IDUser = (SELECT M.IDUser FROM MA_User AS M WHERE M.FacebookID = @FacebookID)

		UPDATE	MA_User_Event
		SET		Attendance = @Attendance
		WHERE	MA_User_Event.IDEvent = @IDEvent 
			AND	MA_User_Event.IDUser = @IDUser	 

	COMMIT TRANSACTION [TranAttend]

	END TRY
	-- If an error occurs in transaction
	BEGIN CATCH
	  ROLLBACK TRANSACTION [TranAttend]
	END CATCH  

END

--EXECUTE PUT_EVENT_ATTENDANCE 1, 'TIY41WTM7HI', 1