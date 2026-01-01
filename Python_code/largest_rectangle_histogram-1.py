# File Name: largest_rectangle_histogram.py

def largestRectangleArea(heights):
    stack = []
    max_area = 0
    heights.append(0)  # Sentinel to empty stack at end

    for i in range(len(heights)):
        while stack and heights[i] < heights[stack[-1]]:
            h = heights[stack.pop()]
            w = i if not stack else i - stack[-1] - 1
            max_area = max(max_area, h * w)
        stack.append(i)

    return max_area


if __name__ == "__main__":
    arr = [2, 1, 5, 6, 2, 3]
    print("Largest Rectangle Area:", largestRectangleArea(arr))
