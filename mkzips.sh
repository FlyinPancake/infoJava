if [ $# -eq 0 ]; then
    echo "Nincs labor megadva"
    echo "Help: -h"
    exit 1
fi

if [ "$*" == "-h" ]; then
	echo "sima labor: ./mkzips.sh 'labor szama'"
	echo "imsc labor: ./mkzips.sh -i 'labor szama'"
	echo ""
	exit 0
fi

LAB=$1

if [ "$1" == "-i" ]; then
	LAB=$2"_imsc"
	echo $LAB
	exit 0
fi

mkdir -p zips
cd "lab$LAB"
zip	"../zips/lab$LAB".zip -r * >> /dev/null
echo "Zipfile created in zips/lab$LAB.zip"
