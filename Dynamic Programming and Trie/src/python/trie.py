class Trie:
    def __init__(self):
        """
        Initialize the Trie data structure here.
        """
        pass

    def insert(self, word: str) -> None:
        """
        Inserts a word into the Trie.

        Parameters:
            word (str): The word to insert. Assume the word is lowercase.

        Returns:
            None
        """
        pass

    def look_up(self, word: str) -> bool:
        """
        Checks if a word has been inserted into the Trie.

        Parameters:
            word (str): The word to look up. Assume it's always lowercase.

        Returns:
            bool: True if the word is found, False otherwise. If 'high' is inserted and the function receives 'hight', it should return False. Only inserted words should return True.
        """
        pass

    def print_tree(self) -> str:
        """
        Prints the Trie in alphabetical order.

        Example:
            internet: (internet)
            interview: (inter(net)(view))
            inter: (inter(net)(view))

        Returns:
            str: A string that shows the state of the Trie.
        """
        pass