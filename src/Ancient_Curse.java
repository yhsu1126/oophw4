
public class Ancient_Curse extends Myskill{
    Ancient_Curse()
    {
	minrange=1;
	maxrange=8;
	cost=1;
	minmulti=2;
	maxmulti=3;
	pattern=1;
	name="Ancient_Curse";
	type=2;
	special=4;// weakness Ap/minmulti Mp/minmulti and only appear when life = life/minmulti
	description="Conditions:available after hp<50% Cost 1 ap Range 1~8 | make the target Ap, Mp decrease half";
    }
}
