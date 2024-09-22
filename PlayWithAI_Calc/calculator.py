def add(*args):
    """
    Adds an arbitrary number of arguments.

    Args:
        *args: Variable length argument list of numbers.

    Returns:
        The sum of the arguments.
    """
    result = 0
    for arg in args:
        result += arg
    return result

def subtract(*args):
    """
    Subtracts an arbitrary number of arguments from the first argument.

    Args:
        *args: Variable length argument list of numbers.

    Returns:
        The result of subtracting the arguments from the first argument.
    """
    result = args[0]
    for arg in args[1:]:
        result -= arg
    return result

def multiply(*args):
    """
    Multiplies an arbitrary number of arguments.

    Args:
        *args: Variable length argument list of numbers.

    Returns:
        The product of the arguments.
    """
    result = 1
    for arg in args:
        result *= arg
    return result

def divide(*args):
    """
    Divides the first argument by the remaining arguments.

    Args:
        *args: Variable length argument list of numbers.

    Returns:
        The result of dividing the first argument by the remaining arguments.
    """
    result = args[0]
    for arg in args[1:]:
        result /= arg
    return result
