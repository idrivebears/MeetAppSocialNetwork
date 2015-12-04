-- FETCH_USER_PROFILE_ATTENDED_EVENTS
-- Obtain user profile -> Attended events

CREATE PROCEDURE FETCH_USER_PROFILE_ATTENDED_EVENTS
(@FACEBOOK_ID	VARCHAR(50))
AS
BEGIN

	SELECT
			Event.IDEvent AS 'EventID',
			Event.Name AS 'EventName',
			Place.Name AS 'PlaceName',
			Event.DateTime AS 'EventDateTime'
	FROM	
			MA_User 
			JOIN MA_User_Event ON(MA_User.IDUser = MA_User_Event.IDUser)
			JOIN Event ON (MA_User_Event.IDEvent = Event.IDEvent)
			JOIN Place ON (Place.IDPlace = Event.IDPlace)
	WHERE	
			Attendance = 1
		AND MA_User.FacebookID = @FACEBOOK_ID
	ORDER BY
			Event.DateTime DESC


END;

EXECUTE FETCH_USER_PROFILE_ATTENDED_EVENTS 'CHA89EUF3LZOAP75SZS1BB'

