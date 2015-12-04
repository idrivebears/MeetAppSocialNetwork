-- --PUT_PLACE
-- Arguments: IDLocation, IDCategory, Name, Description
-- Returns: LocationID

CREATE PROCEDURE PUT_PLACE
(@IDLocation	int,
@IDCategory		int,			-- 1 <= IDCategory <= 6
@Name			varchar(50),
@Description	varchar(500)) 
AS
BEGIN

	BEGIN TRANSACTION [TranPlace]

	BEGIN TRY
		INSERT INTO Place(IDLocation, IDCategory, Name, Description)
		VALUES (@IDLocation, @IDCategory, @Name, @Description)

		-- The last identity value inserted
		SELECT SCOPE_IDENTITY() as 'PlaceID';


	COMMIT TRANSACTION [TranPlace]

	-- If an error occurs in transaction
	END TRY
	BEGIN CATCH
	  ROLLBACK TRANSACTION [TranPlace]
	END CATCH  

END

EXECUTE PUT_PLACE 1, 2, 'PlaceName', 'PLaceDescription'