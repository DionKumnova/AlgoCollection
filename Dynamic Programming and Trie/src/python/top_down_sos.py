from typing import List, Optional

# Macro variables for table values
NONE = 0
FALSE = 1
TRUE = 2


class TopDownSos:
    def __init__(self, sequence: List[int]) -> None:
        """
        Initializes the TopDownSos class and fills the first column and row of the table.

        Parameters:
            sequence (List[int]): An array of positive integers.
        """
        self.sequence = sequence
        total_sum = sum(self.sequence)

    def check_sum(self, k: int) -> Optional[List[int]]:
        """
        Calls a recursive function that fills in the necessary parts of the table.

        Parameters:
            k (int): The target sum.

        Returns:
            Optional[List[int]]: Returns a list of integers that make up the subsequence that can achieve the target sum k. Returns None if it can't be made.
        """
        pass

