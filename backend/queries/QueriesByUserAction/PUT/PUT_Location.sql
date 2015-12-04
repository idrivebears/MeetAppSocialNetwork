--PUT_LOCATION
-- Arguments: Latitude, Longitude
-- Returns: LocationID

CREATE PROCEDURE PUT_LOCATION
(@Latitude	float,
@Longitude float)
AS
BEGIN

	BEGIN TRANSACTION [Tran1]

	BEGIN TRY
		INSERT INTO Location (Latitude, Longitude)
		VALUES (@Latitude, @Longitude)

		-- The last identity value inserted
		SELECT SCOPE_IDENTITY() as 'LocationID';


	COMMIT TRANSACTION [Tran1]

	-- If an error occurs in transaction
	END TRY
	BEGIN CATCH
	  ROLLBACK TRANSACTION [Tran1]
	END CATCH  

END

EXECUTE PUT_LOCATION 60.64, -5.7