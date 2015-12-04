-- FETCH_EVENT_PROFILE_COMMENT_LIST
-- Fetch comments from event. 
-- Arguments: IDEvent
-- Returns: Commentid, Comment text, commment creator and comment datetime

CREATE PROCEDURE FETCH_EVENT_PROFILE_COMMENT_LIST
(@IDEvent int)
AS
BEGIN

	SELECT	
			Comment.IDComment,
			Comment.CommentText,
			Comment.CreationDate,
			MA_User.Name,
			MA_User.LastName
	FROM	
			Comment
			JOIN Event ON (Comment.IDEvent = Event.IDEvent)
			JOIN MA_User ON (MA_User.IDUser = Comment.IDUserCreator)
	WHERE	
			Event.IDEvent = @IDEvent
	ORDER BY
			CreationDate

END

EXECUTE FETCH_EVENT_PROFILE_COMMENT_LIST 1