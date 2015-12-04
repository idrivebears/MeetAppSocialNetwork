-- FETCH_EVENT_PROFILE_EVENT_INFO
-- Fetch event name, description, visivility, datetime, place and creator

CREATE PROCEDURE FETCH_EVENT_PROFILE_EVENT_INFO
(@EVENT_ID	int)
AS
BEGIN

	SELECT	
			Event.Name AS 'EventName',
			Event.Description AS 'EventDescription',
			Event.Visibility AS 'EventVisibility',
			Event.DateTime AS 'EventDateTime',
			Place.Name AS 'PlaceName',
			MA_User.Name AS 'CreatorName',
			MA_User.LastName As 'CreatorLastName'
	FROM	
			Event
			JOIN Place ON (Place.IDPlace = Event.IDPlace)
			JOIN MA_User ON (MA_User.IDUser = Event.IDUser)
	WHERE	
			Event.IDEvent = @EVENT_ID
END

EXECUTE FETCH_EVENT_PROFILE_EVENT_INFO 1