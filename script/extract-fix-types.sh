#!/bin/bash
# Filename: get_fix_types.sh
# Usage: ./get_fix_types.sh sample.xml
# Description: Extract all field types from FIX XML schema file

# Check if filename is provided
if [ $# -eq 0 ]; then
    echo "Error: Please specify a FIX XML file"
    echo "Usage: $0 sample.xml"
    exit 1
fi

FIX_FILE="$1"

# Validate file existence
if [ ! -f "$FIX_FILE" ]; then
    echo "Error: File not found: $FIX_FILE"
    exit 1
fi

# Extract, clean, and deduplicate type values
grep -o ' type="[^"]*"' "$FIX_FILE" | sed 's/type="//;s/"//' | sort | uniq