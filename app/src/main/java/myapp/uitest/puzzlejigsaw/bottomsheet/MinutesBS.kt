import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_minutes.*
import myapp.uitest.puzzlejigsaw.GlobalPreferences
import myapp.uitest.puzzlejigsaw.R
import myapp.uitest.puzzlejigsaw.adapter.NumbersAdapter

class MinutesBS(var listner: OnNumberInteract) : BottomSheetDialogFragment(),
    NumbersAdapter.OnNumberInteract {
    lateinit var globalPreferences: GlobalPreferences


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_minutes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mylist = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        globalPreferences = GlobalPreferences(requireContext())
        numbersRV.adapter = NumbersAdapter(mylist, this)


    }


    override fun getTheme(): Int {
        return R.style.BottomSheetDialogTheme
    }

    override fun onNumberClicked(number: Int) {
        listner.onNumberClicked(number)

    }

    public interface OnNumberInteract {
        fun onNumberClicked(number: Int)

    }

}