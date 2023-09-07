package com.example.leetcode

import java.util.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

fun main() {
    var s1 = "aabcc"
    var s2 = "dbbca"
    var s3 = "aadbbcbcac"

    println(
        isInterleave(s1, s2, s3)
    )
}

fun test() {
    val digits = "234"
    for (i in digits) {
        println(i)
    }
}

//Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
//
//An interleaving of two strings s and t is a configuration where s and t are divided into n and m substrings respectively
fun isInterleave(s1: String, s2: String, s3: String): Boolean {
    var arr1 = s1
    var arr2 = s2
    var left = 1
    var right = s3.length
    var isS1 = false
    if (s1.length + s2.length != s3.length) {
        return false
    }
    if (s1.isEmpty() || s2.isEmpty()) {
        return s1 == s3 || s2 == s3
    }

    while (left != right) {
        for (i in right downTo left) {
            if (arr1.contains(s3.substring(left - 1, i)) && !isS1) {
                arr1 = arr1.removePrefix(s3.substring(left - 1, i))
                left = i + 1
                isS1 = true
                break
            } else if (arr2.contains(s3.substring(left - 1, i))) {
                arr2 = arr2.removePrefix(s3.substring(left - 1, i))
                left = i + 1
                isS1 = false
                break
            }else if (i == left){
                return false
            }
        }
    }
    return if (!isS1) {
        arr1.last() == s3.last()
    } else {
        arr2.last() == s3.last()
    }
}

//LAB 30
//Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
//The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
fun productExceptSelf(nums: IntArray): IntArray {
    val ans = IntArray(nums.size)
    var n = 1
    for (i in nums.indices) {
        ans[i] = n
        n *= nums[i]
    }
    n = 1
    for (i in nums.size - 1 downTo 0) {
        ans[i] = n * ans[i]
        n *= nums[i]
    }
    return ans
}


//LAB 29
//String to Integer (atoi)
fun myAtoi(s: String): Int {
    val listSpecChar = arrayListOf("-", "+")
    var sNum = ""
    for (i in s) {
        if (i.isDigit() || listSpecChar.contains(i.toString())) {
            sNum += i
        }
    }
    return if (sNum == "") 0
    else sNum.toInt()
}

//LAB 28
//Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
fun searchInsert(nums: IntArray, target: Int): Int {
    return if (nums.contains(target)) nums.indexOf(target)
    else nums.find { it > target }?.let { nums.indexOf(it) } ?: (nums.lastIndex + 1)
}

fun searchInsert2(nums: IntArray, target: Int): Int {
    nums.forEachIndexed { index, i ->
        if (i >= target) return index
    }
    return nums.lastIndex + 1
}


//LAB 27
//Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
//Note that you must do this in-place without making a copy of the array.
fun moveZeroes(nums: IntArray): Unit {
    var lastPos = nums.lastIndex
    while (nums[lastPos] == 0) {
        lastPos--
        if (lastPos == -1) break
    }
    var i = 0
    while (i <= nums.lastIndex) {
        if (nums[i] == 0) {
            if (i > lastPos) {
                break
            } else {
                for (j in i until lastPos) {
                    nums[j] = nums[j + 1]
                }
                nums[lastPos] = 0
                lastPos--
            }
        } else {
            i++
        }
    }
}

//LAB 26
//Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
fun permute(nums: IntArray): List<List<Int>> {
    var gt = 1
    val listNumber = arrayListOf<List<Int>>()
    var tg: Int
    var first = nums[0]

    if (nums.size == 1) {
        return arrayListOf(nums.toList())
    }

    for (i in 1..nums.size) {
        gt *= i
    }
    listNumber.add(nums.toList())
    for (i in 2..gt) {
        if (nums.indexOf(first) == nums.size - 1) {
            first = nums[0]
        }
        tg = nums[nums.indexOf(first) + 1]
        nums[nums.indexOf(first) + 1] = first
        nums[nums.indexOf(first)] = tg
        listNumber.add(nums.toList())
    }
    return listNumber
}

/*fun permute3(nums: IntArray): List<List<Int>> {
    var fakeList = mutableListOf<Int>()
    val realList = mutableListOf<List<Int>>()
    if (nums.size == 1) {
        return arrayListOf(nums.toList())
    }
    for (i in nums.indices) {
        val headNumber = nums[i]
        fakeList = nums.toMutableList()
        fakeList.remove(headNumber)
        val firstList = fakeList.add(headNumber)
        realList.addAll(firstList)

    }
}*/

fun permute2(nums: IntArray): List<List<Int>> {
    val res = mutableListOf<List<Int>>()
    fun backtrack(available: MutableList<Int>, current: MutableList<Int>) {
        if (available.isEmpty()) {
            res.add(current.toList()) // Convert to immutable list before adding.
            return
        }
        for (i in available.indices) {
            // Choose the current number
            val num = available[i]
            current.add(num)
            available.removeAt(i)

            // Recursively generate permutations for the remaining numbers
            backtrack(available, current)

            // Undo the choice (backtrack)
            available.add(i, num)
            current.removeAt(current.size - 1)
        }
    }
    backtrack(nums.toMutableList(), mutableListOf())
    return res
}

//LAB 25
//Letter Combinations of a Phone Number
//Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
fun letterCombinations(digits: String): List<String> {
    val hashMap = HashMap<Char, MutableList<String>>()
    hashMap['2'] = mutableListOf("a", "b", "c")
    hashMap['3'] = mutableListOf("d", "e", "f")
    hashMap['4'] = mutableListOf("g", "h", "i")
    hashMap['5'] = mutableListOf("j", "k", "l")
    hashMap['6'] = mutableListOf("m", "n", "o")
    hashMap['7'] = mutableListOf("p", "q", "r", "s")
    hashMap['8'] = mutableListOf("t", "u", "v")
    hashMap['9'] = mutableListOf("w", "x", "y", "z")

    var listString = mutableListOf<String>()
    var listResult = mutableListOf<String>()
    if (digits.isEmpty()) return listString
    for (i in digits) {
        if (listResult.isEmpty()) {
            listString.addAll(hashMap[i]!!)
        } else {
            for (s in hashMap[i]!!) {
                for (p in listResult) {
                    listString.add(p + "" + s)
                }
            }
        }
        listResult = listString.toMutableList()
        listString.clear()
    }
    return listResult
}


fun letterCombinations2(digits: String): List<String> {
    if (digits.isEmpty()) {
        return emptyList()
    }
    val map = linkedMapOf(
        '2' to listOf("a", "b", "c"),
        '3' to listOf("d", "e", "f"),
        '4' to listOf("g", "h", "i"),
        '5' to listOf("j", "k", "l"),
        '6' to listOf("m", "n", "o"),
        '7' to listOf("p", "q", "r", "s"),
        '8' to listOf("t", "u", "v"),
        '9' to listOf("w", "x", "y", "z")
    )
    var result = mutableListOf<String>()
    val list = arrayListOf<String>()
    for (s in digits.indices) {
        if (result.isEmpty()) {
            list.addAll(map[digits[s]]!!)
        } else {
            for (s2 in map[digits[s]]!!) {
                for (i in result) {
                    list.add(i + "" + s2)
                }
            }
        }
        result = list.toMutableList()
        list.clear()
    }
    return result
}


//LAB 24
//You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
fun canJump(nums: IntArray): Boolean {
    var lastPost = nums.size - 1
    for (i in nums.size - 2 downTo 0) {
        if (nums[i] + i >= lastPost) lastPost = i
    }
    return lastPost == 0
}

//LAB 23
//Kth Largest Element in an Array - Without Sorting
fun findKthLargest(nums: IntArray, k: Int): Int {
    val arr = nums.toMutableList()
    var maxk = Int.MIN_VALUE
    for (i in 1..k) {
        var left = 0
        var right = arr.size - 1
        while (left != right) {
            if (arr[left] > arr[right]) {
                right--
            } else {
                left++
            }
        }
        if (i != k) {
            arr.removeAt(left)
        } else {
            maxk = arr[left]
        }
    }
    return maxk
}

//LAB 22
//You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
//
//Find two lines that together with the x-axis form a container, such that the container contains the most water.
//
//Return the maximum amount of water a container can store.
//
//Notice that you may not slant the container.
fun maxArea(height: IntArray): Int {
    var l = 0
    var r = height.size - 1
    var area = 0
    while (l != r) {
        area = max(area, min(height[l], height[r]) * (r - l))
        if (height[l] > height[r]) {
            r -= 1
        } else {
            l += 1
        }
    }
    return area
}

//LAB 21
//Given an integer array nums, find the subarray with the largest sum, and return its sum.
fun maxSubArray(nums: IntArray): Int {
    var maxSum = nums[0]
    var prefixSum = 0
    for (num in nums) {
        prefixSum = if (prefixSum < 0) num else (prefixSum + num)
        maxSum = Math.max(maxSum, prefixSum)
    }
    return maxSum
}


//LAB 20
//Given a string s, return the longest
//palindromic substring in s.
//Input: s = "babad"
//Output: "bab"
fun longestPalindrome(s: String): String {
    if (s.length == 1) return s
    var sMax = "" + s[0]
    for (i in 0..s.length - 2) {
        var subString = ""
        subString += s[i]
        for (j in i + 1 until s.length) {
            subString += s[j]
            if (subString == subString.reversed()) {
                sMax = if (subString.length > sMax.length) subString else sMax
            }
        }
    }
    return sMax
}

fun longestPalindrome2(s: String): String {
    if (s.isEmpty()) return ""
    var start = 0
    var end = 0
    for (i in s.indices) {
        val len1 = expandAroundCenter(s, i, i)
        val len2 = expandAroundCenter(s, i, i + 1)
        val len = maxOf(len1, len2)

        if (len > end - start) {
            start = i - (len - 1) / 2
            end = i + len / 2
        }
    }

    return s.substring(start, end + 1)
}

private fun expandAroundCenter(s: String, left: Int, right: Int): Int {
    var L = left
    var R = right

    while (L >= 0 && R < s.length && s[L] == s[R]) {
        L--
        R++
    }

    return R - L - 1
}

//LAB 19
//Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
fun longestConsecutive(nums: IntArray): Int {
    nums.sort()
    if (nums.size == 1) return 1
    var dmax = 0
    var d = 1
    for (num in 0..nums.size - 2) {
        if (nums[num] + 1 == nums[num + 1]) {
            d++
        } else if (nums[num] != nums[num + 1]) {
            d = 1
        }
        if (d > dmax) {
            dmax = d
        }
    }
    return dmax
}


//LAB 18
//Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
fun rotate(nums: IntArray, k: Int): Unit {
    val cutPos = nums.size - k
    val cutArray = nums.sliceArray(cutPos until nums.size).toMutableList()
    val prevArray = nums.sliceArray(0 until cutPos).toMutableList()
    cutArray.addAll(prevArray)
}

//LAB 17
//Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
fun strStr(haystack: String, needle: String): Int {
    return haystack.indexOf(needle)
}

//LAB 16
//The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
fun majorityElement(nums: IntArray): Int {
    if (nums.size == 1) return nums[0]
    var d = 1
    nums.sort()
    var numCompare = nums[0]
    for (num in 1 until nums.size) {
        if (numCompare == nums[num]) {
            d++
            if (d > nums.size / 2) {
                return numCompare
            }
        } else {
            numCompare = nums[num]
            d = 1
        }
    }
    return 0
}

//LAB 15
//You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.
//
//Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n, return true if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule and false otherwise.
fun canPlaceFlowers(flowerbed: IntArray, n: Int): Boolean {
    var count = 0
    var i = 0
    val length = flowerbed.size

    while (i < length) {
        if (flowerbed[i] == 0) {
            val next = if (i + 1 < length) flowerbed[i + 1] else 0
            val prev = if (i - 1 >= 0) flowerbed[i - 1] else 0

            if (next == 0 && prev == 0) {
                flowerbed[i] = 1
                count++
            }
        }
        i++
    }
    return count >= n
}

//LAB 14
//You are climbing a staircase. It takes n steps to reach the top.
//
//Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
fun climbStairs(n: Int): Int {
    if (n <= 2) return n
    return climbStairs(n - 1) + climbStairs(n - 2)
}

//LAB 13
//Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
fun wordBreak(s: String, wordDict: List<String>): Boolean {
    for (i in wordDict) {
        if (!s.contains(i)) {
            return false
        }
    }
    return true
}

//LAB 12
//Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
fun reverse(x: Int): Int {
    return if (x <= Int.MIN_VALUE || x >= Int.MAX_VALUE) {
        0
    } else {
        val xString = abs(x).toString().reversed()
        if (x >= 0) {
            xString.toInt()
        } else {
            xString.toInt() * (-1)
        }
    }
}

//LAB 11
//Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
fun threeSum(nums: IntArray): List<List<Int>> {
    val n = nums.size
    val result = arrayListOf<List<Int>>()
    for (i in 0 until n - 2) {
        for (j in i + 1 until n - 1) {
            for (k in j + 1 until n) {
                if (nums[i] + nums[j] + nums[k] == 0) {
                    result.add(listOf(nums[i], nums[j], nums[k]).sorted())
                    break
                }
            }
        }
    }
    return result.distinct()
/*
    for (i in 0 until n - 2) {
        if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
            var low = i + 1
            var high = nums.size - 1
            var sum = 0 - nums[i]
            while (low < high) {
                if (nums[i] + nums[low] + nums[high] == 0) {
                    result.add(listOf(nums[i], nums[low], nums[high]))
                    while (low < high && nums[low] == nums[low + 1]) low++
                    while (low < high && nums[high] == nums[high - 1]) high--
                    low++
                    high--
                } else if (nums[low] + nums[high] > sum) {
                    high--
                } else {
                    low++
                }
            }
        }
    }*/
}


//LAB 10
//Write a function to find the longest common prefix string amongst an array of strings.
//
//If there is no common prefix, return an empty string "".
fun longestCommonPrefix(strs: Array<String>): String {
    Arrays.sort(strs)
    var indexControl = 0
    val first = strs[0]
    val second = strs[strs.size - 1]
    while (indexControl < first.length && indexControl < second.length) {
        if (first[indexControl] == second[indexControl]) {
            indexControl++
        } else {
            break
        }
    }
    return first.substring(0, indexControl)
/*    if (strs.isEmpty()) return ""

    var commonPrefix = strs[0]

    for (i in 1 until strs.size) {
        var j = 0
        while (j < commonPrefix.length && j < strs[i].length && commonPrefix[j] == strs[i][j]) {
            j++
        }
        commonPrefix = commonPrefix.substring(0, j)
        if (commonPrefix.isEmpty()) break
    }

    return commonPrefix
    */
}


//LAB 9
fun isAnagram(s: String, t: String): Boolean {
    val sArray = s.toCharArray()
    val tArray = t.toCharArray()
    return sArray.sorted() == tArray.sorted()
}


//LAB 8
//A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
//
//Given a string s, return true if it is a palindrome, or false otherwise.
fun isPalindrome(s: String): Boolean {
    var alphabetString: String = ""
    if (s.trim() == alphabetString) return true
    for (i in s) {
        if (i.isLetter()) {
            if (i.isUpperCase()) {
                alphabetString += i.lowercase()
            } else {
                alphabetString += i
            }
        }
    }
    return alphabetString == alphabetString.reversed()

}


//LAB 7
//Given an integer array nums,
// return true if any value appears at least twice in the array,
// and return false if every element is distinct.
fun containsDuplicate(nums: IntArray): Boolean {
    val distinctArray = nums.distinct().toIntArray()
    if (distinctArray.contentEquals(nums)) return false
    return true
}

//LAB 6
//Given an integer x, return true if x is a
//palindrome
//, and false otherwise.
fun isPalindrome(x: Int): Boolean {
    val numInString = x.toString()
    if (numInString == numInString.reversed())
        return true
    return false
}


//LAB 5
//Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
fun myPow(x: Double, n: Int): Double {
    return x.pow(n.toDouble())
}

fun myPow_2(x: Double, n: Int): Double {
    if (n == 0) return 1.0
    if (n == 1) return x

    val halfPow = myPow(x, n / 2)
    return if (n % 2 == 0) {
        halfPow * halfPow
    } else {
        if (n > 0) x * halfPow * halfPow else (1 / x) * halfPow * halfPow
    }
}


//LAB 4
//You are given an array prices where prices[i] is the price of a given stock on the ith day.
//
//You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
//
//Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
fun maxProfit(prices: IntArray): Int {
    var maxProfit = 0
    var minPrice = Int.MAX_VALUE
    for (price in prices) {
        if (price < minPrice) {
            minPrice = price
        } else {
            if (maxProfit < price - minPrice) {
                maxProfit = price - minPrice
            }
        }
    }
    return maxProfit
}


//LAB 3
//Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
//
//An input string is valid if:
//
//Open brackets must be closed by the same type of brackets.
fun isValid(s: String): Boolean {
    val listChar = arrayListOf("{}", "()", "[]")
    var index = 0
    if (s.length % 2 != 0) return false
    while (index <= s.length - 2) {
        if (!listChar.contains(s.substring(index, index + 2))) return false
        index += 2
    }
    return true
}


//LAB 2
//Given a string s, find the length of the longest
//substring
//without repeating characters.
fun lengthOfLongestSubstring(s: String): Int {
    val n = s.length
    val charset = mutableSetOf<Char>()
    var ans = 0
    var i = 0
    var j = 0
    while (i < n && j < n) {
        if (!charset.contains(s[j])) {
            charset.add(s[j])
            j++
            ans = maxOf(ans, j - i)
        } else {
            charset.remove(s[i])
            i++
        }
    }
    return ans
}


//LAB 1
//Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
//
//You may assume that each input would have exactly one solution, and you may not use the same element twice.
//
//You can return the answer in any order.
fun twoSum(nums: IntArray, target: Int): IntArray {
    for (i in nums.indices - 1) {
        for (j in 0..nums.lastIndex) {
            if (nums[i] + nums[j] == target && i != j) {
                return intArrayOf(i, j)
            }
        }
    }
    return intArrayOf()
}