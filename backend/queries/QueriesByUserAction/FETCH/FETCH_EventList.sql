-- Fetch list on events based on facebookid
-- It shows only events from present and future

CREATE PROCEDURE FETCH_EVENT_LIST
(@FACEBOOK_ID VARCHAR(50),
@ORDER_BY_RECOMMENDATION BINARY)
AS
BEGIN
	IF @ORDER_BY_RECOMMENDATION = 1
		
		-- Order by recommendation weight
		SELECT	EventList.EventName,
				EventList.EventDescription,
				EventList.EventDateTime,
				EventList.PlaceName,
				MA_User.Name as 'CreatorFirstName',
				MA_User.LastName as 'CreatorLastName'
		FROM	
			(SELECT	Event.Name AS 'EventName',
					Event.Description AS 'EventDescription',
					Event.DateTime AS 'EventDateTime',
					Place.Name AS 'PlaceName',
					Event.IDUser AS 'IDCreatorUser',
					Recommendation.Weight
			FROM	MA_User,
					Recommendation,
					Event,
					Place
			WHERE	MA_User.IDUser = Recommendation.IDUser
				AND	Event.IDEvent = Recommendation.IDEvent
				AND MA_User.FacebookID =  @FACEBOOK_ID			
				AND Event.DateTime >= DATEADD(day, -1, convert(date, GETDATE())) -- ONLY PRESENT AND FUTURE
				AND	Place.IDPlace = Event.IDPlace) AS EventList,
			MA_User
		WHERE
				MA_User.IDUser = EventList.IDCreatorUser
		ORDER BY	
				EventList.Weight DESC
	ELSE
		-- Order by date time
		SELECT	EventList.EventName,
				EventList.EventDescription,
				EventList.EventDateTime,
				EventList.PlaceName,
				MA_User.Name AS 'CreatorFirstName',
				MA_User.LastName AS 'CreatorLastName'
		FROM	
			(SELECT	Event.Name AS 'EventName',
					Event.Description AS 'EventDescription',
					Event.DateTime AS 'EventDateTime',
					Place.Name AS 'PlaceName',
					Event.IDUser AS 'IDCreatorUser',
					Recommendation.Weight
			FROM	MA_User,
					Recommendation,
					Event,
					Place
			WHERE	MA_User.IDUser = Recommendation.IDUser
				AND	Event.IDEvent = Recommendation.IDEvent
				AND MA_User.FacebookID = @FACEBOOK_ID
				AND Event.DateTime >= DATEADD(day, -1, convert(date, GETDATE())) -- ONLY PRESENT AND FUTURE
				AND	Place.IDPlace = Event.IDPlace) AS EventList,
			MA_User
		WHERE
				MA_User.IDUser = EventList.IDCreatorUser
		ORDER BY	
				EventList.EventDateTime
END

EXECUTE FETCH_EVENT_LIST 'CHA89EUF3LZOAP75SZS1BB', 1
