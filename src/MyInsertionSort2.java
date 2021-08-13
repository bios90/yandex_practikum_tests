import java.io.IOException;

public class MyInsertionSort2
{
//    [4,8,9,6,0,1,5,7,2,3]

    private static int[] nums_word_length = new int[]
            {
                    "ноль".length(),
                    "один".length(),
                    "два".length(),
                    "три".length(),
                    "четыре".length(),
                    "пять".length(),
                    "шесть".length(),
                    "семь".length(),
                    "восемь".length(),
                    "девять".length()
            };

    public static void main(String[] args)
    {
//        int[] nums = getNums();
//        sortInsertion(nums);
//        sortInsertionWithKey(nums, getKeyGetter());
//        sortInsertionWithComparator(nums, getComparator());
//        sortInsertionWithComparator(nums, getComparatorNew());
//        printArray(nums);
        CityData[] cities = CityData.getTestData();
        sortInsertionWithComparatorGenerics(cities, getComparatorForCities());
        printArrayOfCities(cities);
    }

    private static int[] getNums()
    {
        return new int[]{6, 2, 7, 4, 9, 0, 8, 3, 1, 5};
    }

    private static void sortInsertion(int[] nums)
    {
        for (int i = 1; i < nums.length; i++)
        {
            int num_to_sort = nums[i];
            int j;
            for (j = i; j > 0; j--)
            {
                if (nums[j - 1] > num_to_sort)
                {
                    nums[j] = nums[j - 1];
                } else
                {
                    break;
                }
            }
            nums[j] = num_to_sort;
        }
    }

    private interface Comparator<T>
    {
        public boolean compare(T obj_1, T obj_2);
    }

    private interface KeyGetter
    {
        public int getKey(int num);
    }

    private static KeyGetter getKeyGetter()
    {
        KeyGetter keyGetter = new KeyGetter()
        {
            @Override
            public int getKey(int num)
            {
                return nums_word_length[num];
            }
        };

        return keyGetter;
    }

    private static Comparator<Integer> getComparator()
    {
        return new Comparator<Integer>()
        {
            @Override
            public boolean compare(Integer obj_1, Integer obj_2)
            {
                return nums_word_length[obj_1] > nums_word_length[obj_2];
            }
        };
    }

    private static Comparator<Integer> getComparatorNew()
    {

        return new Comparator<Integer>()
        {
            @Override
            public boolean compare(Integer obj_1, Integer obj_2)
            {
                int length_1 = nums_word_length[obj_1];
                int length_2 = nums_word_length[obj_2];
                if (length_1 != length_2)
                {
                    return length_1 < length_2;
                } else
                {
                    return obj_1 > obj_2;
                }
            }
        };
    }

    private static Comparator<CityData> getComparatorForCities()
    {
        return new Comparator<CityData>()
        {
            @Override
            public boolean compare(CityData obj_1, CityData obj_2)
            {
                int length_1 = obj_1.name.length();
                int length_2 = obj_2.name.length();
                if (length_1 != length_2)
                {
                    return length_1 > length_2;
                } else
                {
                    return obj_1.pos > obj_2.pos;
                }
            }
        };
    }

    private static void sortInsertionWithKey(int[] nums, KeyGetter key)
    {
        for (int i = 1; i < nums.length; i++)
        {
            int num_to_sort = nums[i];
            int j;
            for (j = i; j > 0; j--)
            {
                if (key.getKey(nums[j - 1]) > key.getKey(num_to_sort))
                {
                    nums[j] = nums[j - 1];
                } else
                {
                    break;
                }
            }
            nums[j] = num_to_sort;
        }
    }

    private static void sortInsertionWithComparator(int[] nums, Comparator comparator)
    {
        for (int i = 1; i < nums.length; i++)
        {
            int num_to_sort = nums[i];
            int j;
            for (j = i; j > 0; j--)
            {
                if (comparator.compare(nums[j - 1], num_to_sort))
                {
                    nums[j] = nums[j - 1];
                } else
                {
                    break;
                }
            }
            nums[j] = num_to_sort;
        }
    }

    private static <T> void sortInsertionWithComparatorGenerics(T[] nums, Comparator<T> comparator)
    {
        for (int i = 1; i < nums.length; i++)
        {
            T obj = nums[i];
            int j;
            for (j = i; j > 0; j--)
            {
                if (comparator.compare(nums[j - 1], obj))
                {
                    nums[j] = nums[j - 1];
                } else
                {
                    break;
                }
            }
            nums[j] = obj;
        }
    }

    private static void printArray(int[] nums)
    {
        StringBuilder out = new StringBuilder();
        for (int num : nums)
        {
            out.append(num).append(" ");
        }
        System.out.println(out.toString().trim());
    }

    private static void printArrayOfCities(CityData[] cities)
    {
        StringBuilder out = new StringBuilder();
        for (CityData city : cities)
        {
            out.append(city.name).append(" ");
        }
        System.out.println(out.toString().trim());
    }
}

class CityData
{
    String name;
    int pos;

    public CityData(String name, int pos)
    {
        this.name = name;
        this.pos = pos;
    }

    public static CityData[] getTestData()
    {
        return new CityData[]
                {
                        new CityData("Москва", 0),
                        new CityData("Казань", 1),
                        new CityData("Питер", 2),
                };
    }
}
