-- FETCH_PLACE_PROFILE
-- Obtain place profile -> name, category, location, description

CREATE PROCEDURE FETCH_PLACE_PROFILE
(@PlaceID int)
AS
BEGIN

SELECT	
		Place.Name AS 'PlaceName',
		Category.Name AS 'Category',
		Place.Description AS 'PlaceDescription',
		Location.Latitude,
		Location.Longitude
FROM	
		Place
		JOIN Category ON (Category.IDCategory = Place.IDCategory)
		JOIN Location ON (Location.IDLocation = PLACE.IDLocation)
WHERE	
		Place.IDPlace = @PlaceID

END

EXECUTE FETCH_PLACE_PROFILE 1