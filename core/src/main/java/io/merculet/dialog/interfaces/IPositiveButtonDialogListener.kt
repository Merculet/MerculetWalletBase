package io.merculet.dialog.interfaces

import io.merculet.dialog.interfaces.DialogListener

/**
 * Implement this interface in Activity or Fragment to react to positive dialog buttons.
 *
 * @author Tomáš Kypta
 * @since 2.1.0
 */
interface IPositiveButtonDialogListener : DialogListener {

    fun onPositiveButtonClicked(requestCode: Int)
}
