// ============================================================================
//   Copyright 2006, 2007 Daniel W. Dyer
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.
// ============================================================================
package org.uncommons.watchmaker.examples.sudoku;

/**
 * A potential solution for a Sudoku puzzle.
 * @author Daniel Dyer
 */
public final class Sudoku
{
    /** The dimensions (in cells) of the puzzle square. */
    public static final int SIZE = 9;
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = SIZE;

    private final Cell[][] cells;


    /**
     * Creates a sudoku solution from a 2-dimensional array of cells.
     */
    public Sudoku(Cell[][] cells)
    {
        if (cells.length != SIZE)
        {
            throw new IllegalArgumentException("Sudoku must have 9 rows.");
        }
        if (cells[0].length != SIZE) // Should really check all rows because the array may not be square.
        {
            throw new IllegalArgumentException("Sudoku must have 9 columns.");
        }
        this.cells = cells;
    }


    public int getValue(int row, int column)
    {
        return cells[row][column].getValue();
    }


    public boolean isFixed(int row, int column)
    {
        return cells[row][column].isFixed();
    }


    /**
     * Returns an array of cells that make up a row.  The array
     * returned is a clone of the underlying data structure and
     * therefore can be modified without affecting this object.
     * @return A row of cells from this Sudoku grid.
     */
    public Cell[] getRow(int row)
    {
        return cells[row].clone();
    }


    @Override
    public String toString()
    {
        StringBuilder buffer = new StringBuilder();
        for (Cell[] row : cells)
        {
            for (Cell cell : row)
            {
                buffer.append(' ');
                buffer.append(cell.getValue());
            }
            buffer.append('\n');
        }
        return buffer.toString();
    }


    /**
     * A single cell in a sudoku grid.  Contains a value and may be fixed (i.e. it is
     * one of the given cells at the start).
     */
    public static final class Cell
    {
        private final int value;
        private final boolean fixed;

        public Cell(int value, boolean fixed)
        {
            if (value < MIN_VALUE || value > MAX_VALUE)
            {
                throw new IllegalArgumentException("Value must be between 1 and 9.");
            }
            this.value = value;
            this.fixed = fixed;
        }

        public int getValue()
        {
            return value;
        }

        public boolean isFixed()
        {
            return fixed;
        }
    }
}