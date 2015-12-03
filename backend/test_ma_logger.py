import unittest
from ma_logger import Logger

class test_ma_logger(unittest.TestCase):
    def test_singleton(self):
        firstInstance = Logger()
        secondInstance = Logger()
        self.assertEquals(firstInstance, secondInstance)
        pass
    def test_action_registry(self):
        loggerInstance = Logger()
        action1 = "Test Action 1"
        loggerInstance.registerNewAction("Test Action 1")
        actionRead = loggerInstance.getLatestAction()
        containsAction = action1 in actionRead
        self.assertTrue(containsAction)
        pass
    def test_action_registry_different_objects(self):
    	loggerInstance2 = Logger()
    	actionRead = loggerInstance2.getLatestAction()
    	action1 = "Test Action 1"
    	containsAction = action1 in actionRead
    	self.assertTrue(containsAction)
    def test_empty_on_log(self):
    	logger = Logger()
    	logger.log()
    	latestAction = logger.getLatestAction()
    	self.assertEquals(latestAction, "")


if __name__ == '__main__':
    unittest.main()
