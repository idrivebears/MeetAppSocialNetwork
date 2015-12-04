-- PUT_EVENT_PROFILE
--	, PlaceID int, FacebookIDCreator varchar(50), EventName varchar(50), EventDescription varchar(50), 
--	EventDateTime datetime, Visibility varchar(50)

CREATE PROCEDURE PUT_EVENT_PROFILE
(@IDPlace			int,
@FacebookID	varchar(50),
@Name			varchar(50),
@Description	varchar(500),
@DateTime		datetime,
@Visibility		varchar(50)) -- Public|Private
AS
BEGIN

	-- The last id inserted, necessary for other queries
	DECLARE @NewEventIdentity INT

	-- Temporary friend list table
	CREATE TABLE #TMP_FRIEND_LIST
	(RowId			int,
	IDFriendUser	int)


	BEGIN TRANSACTION [TranEvent]

	BEGIN TRY
			
		-- Obtain IDUser
		DECLARE @IDCreatorUser int
		SET @IDCreatorUser = (SELECT M.IDUser FROM MA_User AS M WHERE M.FacebookID = @FacebookID)

		-- Create new event
		INSERT INTO Event(IDPlace, IDUser, Name, Description, DateTime, Visibility)
		VALUES (@IDPlace, @IDCreatorUser, @Name, @Description, @DateTime, @Visibility)

		-- The last identity value inserted
		SELECT @NewEventIdentity = SCOPE_IDENTITY()

		-- Get list of all friends. Visibility is not yet taken into account
		INSERT INTO #TMP_FRIEND_LIST EXECUTE FETCH_FRIEND_LIST_WITH_ROWID @IDCreatorUser
		
		-- Variables to visit each friend
		DECLARE @friendListCount INT 
		SET @friendListCount  = (SELECT COUNT(*) FROM #TMP_FRIEND_LIST)

		-- for each friend, create a recommendation and a relation between MA_User_Event
		DECLARE @friendIndex int = 1
		WHILE @friendIndex <= @friendListCount BEGIN
			
			-- Create recommendation, weigth = 0 				
			INSERT INTO Recommendation (IDEvent, IDUser, Weight)
			VALUES (@NewEventIdentity, (SELECT T.IDFriendUser FROM #TMP_FRIEND_LIST AS T WHERE T.RowId = @friendIndex), 0)

			-- Create relation between user and event, with attendance = 0				
			INSERT INTO MA_User_Event (IDEvent, IDUser, Attendance)
			VALUES (@NewEventIdentity, (SELECT T.IDFriendUser FROM #TMP_FRIEND_LIST AS T WHERE T.RowId = @friendIndex), 0)

			SET @friendIndex = @friendIndex + 1
		END


	COMMIT TRANSACTION [TranEvent]

	-- If an error occurs in transaction
	END TRY
	BEGIN CATCH
	  ROLLBACK TRANSACTION [TranEvent]
	END CATCH  

END

--EXECUTE PUT_EVENT_PROFILE 3, 'JPE40NAY7LV', 'Event ', 'Description ', '2015-11-29', 'Public'