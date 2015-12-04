-- FETCH_USER_PROFILE_CREATED_EVENTS
-- Obtain user profile -> Created events

CREATE PROCEDURE FETCH_USER_PROFILE_CREATED_EVENTS
(@FACEBOOK_ID VARCHAR(50))
AS
BEGIN

	SELECT
			Event.IDEvent AS 'EventID',
			Event.Name AS 'EventName',
			Place.Name AS 'PlaceName',
			Event.DateTime AS 'EventDateTime'
	FROM	
			MA_User
			JOIN Event ON (MA_User.IDUser = Event.IDUser) 
			JOIN Place ON (Place.IDPlace = Event.IDPlace)
	WHERE	
			MA_User.FacebookID = @FACEBOOK_ID
	ORDER BY
			Event.DateTime DESC
END

EXECUTE FETCH_USER_PROFILE_CREATED_EVENTS 'XQC90FFR6RRSGM50KLB2RY'
